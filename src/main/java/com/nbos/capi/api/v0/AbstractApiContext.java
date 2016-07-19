package com.nbos.capi.api.v0;

import java.util.HashMap;

/**
 * Created by vivekkiran on 6/29/16.
 */

public abstract class AbstractApiContext implements ApiContext {

    static HashMap<String,ApiContext> apiContexts = new HashMap<>();

    // name of the context instance
    protected String name="app";

    public static void registerApiContext(ApiContext apiContext) {
        apiContexts.put(apiContext.getName(),apiContext);
    }

    public static ApiContext get(String name) {
        ApiContext ctx = apiContexts.get(name);
        if (ctx==null) {
            ctx = new InMemoryApiContext(name);
            registerApiContext(ctx);
        }
        return ctx;
    }



    public AbstractApiContext() {}
    public AbstractApiContext(String name) {
        this.name=name;
    }
}