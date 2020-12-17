package com.example.starter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationContext;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthorizationHandler;
import io.vertx.ext.web.handler.impl.HttpStatusException;

// Almost exact copy of AuthorizationHandlerImpl
public class AppAuthorizationHandler implements AuthorizationHandler {

  private final static Logger LOG = LoggerFactory.getLogger(AuthorizationHandler.class);

  private final static int FORBIDDEN_CODE = 403;
  private final static HttpStatusException FORBIDDEN_EXCEPTION = new HttpStatusException(FORBIDDEN_CODE);

  private final Authorization authorization;
  private final Collection<AuthorizationProvider> authorizationProviders;
  private BiConsumer<RoutingContext, AuthorizationContext> variableHandler;

  public AppAuthorizationHandler(Authorization authorization) {
    this.authorization = Objects.requireNonNull(authorization);
    this.authorizationProviders = new ArrayList<>();
  }

  @Override
  public void handle(RoutingContext routingContext) {
    if (routingContext.user() == null) {
      routingContext.fail(FORBIDDEN_CODE, FORBIDDEN_EXCEPTION);
    } else {
      // create the authorization context
      AuthorizationContext authorizationContext = getAuthorizationContext(routingContext);
      // check or fetch authorizations
      checkOrFetchAuthorizations(routingContext, authorizationContext, authorizationProviders.iterator());
    }
  }

  @Override
  public AuthorizationHandler variableConsumer(BiConsumer<RoutingContext, AuthorizationContext> handler) {
    this.variableHandler = handler;
    return this;
  }

  /**
   * this method checks that the specified authorization match the current content.
   * It doesn't fetch all providers at once in order to do early-out, but rather tries to be smart and fetch authorizations one provider at a time
   *
   * @param routingContext
   * @param authorizationContext
   * @param providers
   */
  private void checkOrFetchAuthorizations(RoutingContext routingContext, AuthorizationContext authorizationContext, Iterator<AuthorizationProvider> providers) {
    if (authorization.match(authorizationContext)) {
      routingContext.next();
      return;
    }
    if (!providers.hasNext()) {
      routingContext.fail(FORBIDDEN_CODE, FORBIDDEN_EXCEPTION);
      return;
    }

    // there was no match, in this case we do the following:
    // 1) contact the next provider we haven't contacted yet
    // 2) if there is a match, get out right away otherwise repeat 1)
    while (providers.hasNext()) {
      AuthorizationProvider provider = providers.next();
      // we haven't fetch authorization from this provider yet
      if (! routingContext.user().authorizations().getProviderIds().contains(provider.getId())) {
        provider.getAuthorizations(routingContext.user(), authorizationResult -> {
          if (authorizationResult.failed()) {
            LOG.warn("An error occured getting authorization - providerId: " + provider.getId(), authorizationResult.cause());
            // note that we don't 'record' the fact that we tried to fetch the authorization provider. therefore it will be re-fetched later-on
          }
          checkOrFetchAuthorizations(routingContext, authorizationContext, providers);
        });
        // get out right now as the callback will decide what to do next
        return;
      }
    }

    // This is the only difference with AuthorizationHandlerImpl.
    // It fixes a bug where routingContext.fail is never
    // called if all authorizations have already been fetched,
    // which can be the case after this function has been executed,
    // since the authorizations are persisted in the session.
    routingContext.fail(FORBIDDEN_CODE, FORBIDDEN_EXCEPTION);
  }

  private AuthorizationContext getAuthorizationContext(RoutingContext event) {
    final AuthorizationContext result = AuthorizationContext.create(event.user());
    if (variableHandler != null) {
      variableHandler.accept(event, result);
    }
    return result;
  }

  @Override
  public AuthorizationHandler addAuthorizationProvider(AuthorizationProvider authorizationProvider) {
    Objects.requireNonNull(authorizationProvider);

    this.authorizationProviders.add(authorizationProvider);
    return this;
  }
}
