package com.nbos.capi.api.v0;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivekkiran on 6/23/16.
 */

public class InMemoryApiContext implements ApiContext {

    HashMap<String,Object> store = new HashMap<>();
    HashMap<String,String> hosts = new HashMap<>();
    HashMap<String,TokenApiModel> tokens = new HashMap<>();

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

    public void setClientToken(TokenApiModel tokenApiModel) {
        store.put("token.client",tokenApiModel);
    }
    public TokenApiModel getClientToken() {
        return (TokenApiModel)store.get("token.client");
    }

    public void setUserToken(String moduleName, TokenApiModel tokenApiModel) {
        tokens.put(moduleName,tokenApiModel);
        if(tokens.get(".")==null) {
            tokens.put(".",tokenApiModel);
        }
    }

    public TokenApiModel getUserToken(String moduleName) {
        TokenApiModel tokenApiModel = (TokenApiModel)tokens.get(moduleName);
        if(tokenApiModel==null) {
            tokenApiModel = (TokenApiModel)tokens.get(".");
        }
        return tokenApiModel;
    }

    @Override
    public void setHost(String moduleName, String host) {
        hosts.put(moduleName,host);
    }

    @Override
    public String getHost(String moduleName) {
        return hosts.get(moduleName);
    }

}
