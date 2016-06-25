package com.nbos.capi.api.v0;

import java.util.HashMap;

/**
 * Created by vivekkiran on 6/23/16.
 */

public class InMemoryApiContext implements ApiContext {

    HashMap<String,Object> store = new HashMap<>();

    @Override
    public Object get(String context) {
        return store.get(context);
    }

    @Override
    public void set(String context, Object tokenApiModel) {
        store.put(context,tokenApiModel);
    }

    @Override
    public void setToken(String context, TokenApiModel tokenApiModel) {
        set("token."+context,tokenApiModel);
    }

    @Override
    public TokenApiModel getToken(String context) {
        return (TokenApiModel)get("token."+context);;
    }

    @Override
    public void setHost(String host) {
        set("api.host",host);
    }

    @Override
    public String getHost() {
        return (String)get("api.host");
    }

}
