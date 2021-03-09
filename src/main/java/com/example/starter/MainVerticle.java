package com.example.starter;

import com.example.starter.db.UserMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import jooq.tables.daos.DbBlogUserDao;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.openapitools.vertxweb.server.WebSocketService;
import org.openapitools.vertxweb.server.model.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    try {
      Configuration jooqConfiguration = new DefaultConfiguration();
      jooqConfiguration.set(SQLDialect.POSTGRES);
      String confFilePath = System.getenv("BLOG_CONF");
      JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
      PgConnectOptions pgConnectOptions = new PgConnectOptions(config.getJsonObject("database"));
      PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
      PgPool pgPool = PgPool.pool(vertx, pgConnectOptions, poolOptions);
      ArticleController articleController = new ArticleController(jooqConfiguration, pgPool);
      RegistrationController registrationController = new RegistrationController(jooqConfiguration, pgPool);
      AuthorizationService authorizationService = new AuthorizationService();
      UserService userService = new UserService(jooqConfiguration, pgPool, authorizationService);
      HttpSessionController httpSessionController = new HttpSessionController(jooqConfiguration, pgPool, userService);

      SessionStore sessionStore = LocalSessionStore.create(vertx);
      SessionHandler sessionHandler = SessionHandler.create(sessionStore);
      Future<RouterBuilder> routerBuilder$ = RouterBuilder.create(vertx, "openapi.yaml");
      AppAuthenticationProvider authenticationProvider = new AppAuthenticationProvider(userService);
      UserController userController = new UserController(userService);
      WebSocketService webSocketService = new WebSocketService();
      ArticleCommentController articleCommentController = new ArticleCommentController(jooqConfiguration, pgPool);
      UserMapper userMapper = new UserMapper();
      Future<HttpServer> startServer$ = routerBuilder$.compose(routerBuilder -> {
        routerBuilder.rootHandler(routingContext -> {
          routingContext.response()
            .putHeader("Access-Control-Allow-Origin", "http://localhost:3000")
            .putHeader("Access-Control-Allow-Methods", "PUT,POST,HEAD,GET,OPTIONS")
            .putHeader("Access-Control-Allow-Credentials", "true")
            .putHeader("Access-Control-Allow-Headers", "content-type, cookie");
          if (routingContext.request().method().equals(HttpMethod.OPTIONS)) {
            routingContext.response().end();
            return;
          }
          routingContext.next();
        });
        routerBuilder.rootHandler(sessionHandler);
        routerBuilder.securityHandler(
          "cookieAuth",
          new AppAuthenticationHandler(authenticationProvider, jooqConfiguration, pgPool, userService));

        routerBuilder.operation("insertArticle")
          .handler(
            new AppAuthorizationHandler(PermissionBasedAuthorization.create("article_create")))
          .handler(articleController::insertArticle);
        routerBuilder.operation("updateArticle")
          .handler(articleController::updateArticle);
        routerBuilder.operation("getAllArticles")
          .handler(articleController::getAllArticles);
        routerBuilder.operation("getAllArticlesOfUser")
          .handler(articleController::getAllArticlesOfUser);
        routerBuilder.operation("getArticleById")
          .handler(articleController::getArticleById);
        routerBuilder.operation("getAllCommentsOfArticle")
          .handler(articleCommentController::getAllCommentsOfArticle);
        routerBuilder.operation("insertArticleComment")
          .handler(
            new AppAuthorizationHandler(PermissionBasedAuthorization.create("article_comment_create"))
          )
          .handler(articleCommentController::insertArticleComment);

        routerBuilder.operation("getUserByUsername")
          .handler(userController::getUserByUsername);

        routerBuilder.operation("register")
          .handler(registrationController::register);

        routerBuilder.operation("getCurrentAuthenticatedUser")
          .handler(new AppAuthorizationHandler(RoleBasedAuthorization.create("user")))
          .handler(httpSessionController::getCurrentAuthenticatedUser);
        routerBuilder.operation("logout")
          .handler(httpSessionController::logout);

        // this isn't in HttpSessionController.login()
        // because AppLoginHandler extends AuthenticationHandlerImpl
        routerBuilder.operation("login").handler(new AppLoginHandler(
          authenticationProvider,
          userService,
          userMapper
        ));
        Router router = routerBuilder.createRouter();

        SockJSHandlerOptions options = new SockJSHandlerOptions()
          .setHeartbeatInterval(2000);

        List<SockJSSocket> connectedSockets = new ArrayList<>();

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);
        router.mountSubRouter("/websocket", sockJSHandler.socketHandler((SockJSSocket sock) -> {
          connectedSockets.add(sock);
          System.out.println("Received new socket connection.");
          System.out.println("Number of sockets connected: " + connectedSockets.size());
          sock.handler((Buffer buffer) -> {
            String fromBuffer = buffer.toString(StandardCharsets.UTF_8);
            System.out.println("Received WebSocket message:");
            System.out.println(fromBuffer);
            SessionUser sessionUser = (SessionUser) sock.webUser();
            try {
              WebSocketMessage webSocketMessage = webSocketService.parseMessage(buffer);
              if (webSocketMessage instanceof WebSocketClientChatMessage) {
                WebSocketClientChatMessage clientChatMessage = (WebSocketClientChatMessage) webSocketMessage;
                WebSocketServerChatMessage serverChatMessage = webSocketService.newServerChatMessage(
                  sessionUser.getUsername(),
                  clientChatMessage.getMessage()
                );
                for (SockJSSocket connectedSocket : connectedSockets) {
                  connectedSocket.write(JsonObject.mapFrom(serverChatMessage).toBuffer());
                }
              }
            } catch (Throwable t) {
              System.err.println("Received invalid message :");
              t.printStackTrace();
              return;
            }
          });
          sock.endHandler((Void end) -> {
            connectedSockets.remove(sock);
          });
        }));
        router.errorHandler(500, routingContext -> {
          routingContext.failure().printStackTrace();
          routingContext.response().setStatusCode(500).end();
        });

        HttpServer server = vertx.createHttpServer(new HttpServerOptions().setPort(8081));
        return server.requestHandler(router).listen();
      });
      startServer$.onSuccess((HttpServer httpServer) -> {
        startPromise.complete();
        System.out.println("HTTP server started on port 8081");
      });
      startServer$.onFailure((e) -> {
        e.printStackTrace();
        startPromise.fail(e);
      });
    } catch (Throwable e) {
      e.printStackTrace();
      startPromise.fail(e);
    }
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }
}
