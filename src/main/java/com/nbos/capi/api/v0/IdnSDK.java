package com.nbos.capi.api.v0;

/**
 * Created by vivekkiran on 6/27/16.
 */

public class IdnSDK {
    public static final void init(ApiContext apiContext){
        AbstractApiContext.registerApiContext(apiContext);
        try {
            Class.forName("com.nbos.capi.modules.identity.v0.IdentityIdsRegistry");
        } catch(Exception e) {
            System.out.println("unable to load");
        }
    }
}
