package com.nbos.capi.api.v0;

import com.nbos.capi.api.v0.models.TokenApiModel;

import java.util.Map;

/**
 * Created by vivekkiran on 6/23/16.
 */

public interface ApiContext {

    // context name, this should give the ability to have as many contexts as possible
    // 'app' context, 'api' context, etc..
    String getName();

    /**
     * opportunity for the context to initialize itself, if it needs to read any properties from
     * config files etc..
     */
    void init();

    void setHost(String moduleName, String host);

    String getHost(String moduleName);


    void setClientCredentials(Map map);

    void setClientToken(TokenApiModel tokenApiModel);

    Map getClientCredentials();

    TokenApiModel getClientToken();

    void setUserToken(String moduleName, TokenApiModel tokenApiModel);

    TokenApiModel getUserToken(String moduleName);


    /*
     * property
     *   "user.token"
     *   "client.token"
     *   "host"
     * @param property
     * @param value
     * we may have to re-evaluate the need of this generic API
     */
//    void set(String property, Object value);
//    Object get(String property);
}
