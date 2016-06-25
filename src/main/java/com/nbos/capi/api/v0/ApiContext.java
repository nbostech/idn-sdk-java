package com.nbos.capi.api.v0;

/**
 * Created by vivekkiran on 6/23/16.
 */

public interface ApiContext {
    /**
     * setToken()
     * getToken()
     * getHost()
     * setHost()
     *
     * @param property
     * @param value
     */

    void setToken(String context,TokenApiModel tokenApiModel);
    TokenApiModel getToken(String context);

    void setHost(String host);
    String getHost();


    /*
     * property
     *   "user.token"
     *   "client.token"
     *   "host"
     * @param property
     * @param value
     */
    void set(String property, Object value);
    Object get(String property);
}
