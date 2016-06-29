package com.nbos.capi.api.v0;

/**
 * Created by vivekkiran on 6/29/16.
 */

public abstract class AbstractApiContext implements ApiContext {

    static ApiContext apiContext = null;

    public static void registerApiContext(ApiContext apiContext) {
        AbstractApiContext.apiContext = apiContext;
    }

    public static ApiContext get() {
        if (apiContext == null) {
            apiContext = new InMemoryApiContext();
        }
        return AbstractApiContext.apiContext;
    }
}