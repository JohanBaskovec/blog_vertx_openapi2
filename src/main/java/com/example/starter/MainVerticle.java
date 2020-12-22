package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
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
import java.util.Base64;
import java.util.List;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    try {
      Configuration configuration = new DefaultConfiguration();
      configuration.set(SQLDialect.POSTGRES);
      String confFilePath = System.getenv("BLOG_CONF");
      JsonObject config = new JsonObject(vertx.fileSystem().readFileBlocking(confFilePath));
      PgConnectOptions pgConnectOptions = new PgConnectOptions(config.getJsonObject("database"));
      PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
      PgPool pool = PgPool.pool(vertx, pgConnectOptions, poolOptions);
      ArticleController articleController = new ArticleController(configuration, pool);
      RegistrationController registrationController = new RegistrationController(configuration, pool);
      UserService userService = new UserService();
      HttpSessionController httpSessionController = new HttpSessionController(configuration, pool, userService);

      SessionStore sessionStore = LocalSessionStore.create(vertx);
      SessionHandler sessionHandler = SessionHandler.create(sessionStore);
      Future<RouterBuilder> routerBuilder$ = RouterBuilder.create(vertx, "openapi.yaml");
      AppAuthenticationProvider authenticationProvider = new AppAuthenticationProvider(new DbBlogUserDao(configuration, pool));
      AppAuthorizationProvider authorizationProvider = new AppAuthorizationProvider(configuration, pool);
      AuthorizationService authorizationService = new AuthorizationService();
      UserController userController = new UserController(configuration, pool, authorizationService);
      WebSocketService webSocketService = new WebSocketService();
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
          new AppAuthenticationHandler(authenticationProvider, configuration, pool));

        routerBuilder.operation("insertArticle")
          .handler(
            new AppAuthorizationHandler(RoleBasedAuthorization.create("admin"))
              .addAuthorizationProvider(authorizationProvider))
          .handler(articleController::insertArticle);
        routerBuilder.operation("updateArticle")
          .handler(
            new AppAuthorizationHandler(RoleBasedAuthorization.create("admin"))
              .addAuthorizationProvider(authorizationProvider))
          .handler(articleController::updateArticle);
        routerBuilder.operation("getAllArticles")
          .handler(articleController::getAllArticles);
        routerBuilder.operation("getAllArticlesOfUser")
          .handler(articleController::getAllArticlesOfUser);
        routerBuilder.operation("getArticleById")
          .handler(articleController::getArticleById);

        routerBuilder.operation("getUserByUsername")
          .handler(userController::getUserByUsername);

        routerBuilder.operation("register")
          .handler(registrationController::register);

        routerBuilder.operation("getCurrentAuthenticatedUser")
          .handler(
            new AppAuthorizationHandler(RoleBasedAuthorization.create("user"))
              .addAuthorizationProvider(authorizationProvider)
          )
          .handler(httpSessionController::getCurrentAuthenticatedUser);
        routerBuilder.operation("logout")
          .handler(httpSessionController::logout);

        // this isn't in HttpSessionController.login()
        // because AppLoginHandler extends AuthenticationHandlerImpl
        routerBuilder.operation("login").handler(new AppLoginHandler(
          authenticationProvider,
          authorizationProvider,
          userService
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
            AppUser sessionUser = (AppUser) sock.webUser();
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
