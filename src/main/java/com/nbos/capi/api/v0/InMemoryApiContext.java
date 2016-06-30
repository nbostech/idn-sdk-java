package com.nbos.capi.api.v0;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivekkiran on 6/23/16.
 */

public class InMemoryApiContext implements ApiContext {

    HashMap<String,Object> store = new HashMap<>();
    HashMap<String,String> hosts = new HashMap<>();

    @Override
    public void init() {}
    @Override
    public Map getClientCredentials() {
        return (Map)store.get("client.credentials");
    }

    @Override
    public void setClientCredentials(Map map) {
        store.put("client.credentials",map);
    }

    public void setClientToken(TokenApiModel tokenApiModel) { store.put("token.client",tokenApiModel);}
    public TokenApiModel getClientToken() { return (TokenApiModel)store.get("token.client");}

    public void setUserToken(TokenApiModel tokenApiModel) { store.put("token.user",tokenApiModel); }
    public TokenApiModel getUserToken() { return (TokenApiModel)store.get("token.user"); }

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
        return (TokenApiModel)get("token."+context);
    }

    @Override
    public void setHost(String moduleName, String host) {
        set("api.host."+moduleName,host);
    }

    @Override
    public String getHost(String moduleName) {
        return (String)get("api.host."+moduleName);
    }

}
