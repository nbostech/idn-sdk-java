package com.nbos.capi.api.v0;

import com.nbos.capi.modules.identity.v0.IdentityApi;
import com.nbos.capi.modules.ids.v0.IDS;

/**
 * Created by vivekkiran on 6/27/16.
 */

public class IdnSDK {
    public static final void init(ApiContext apiContext) {
        AbstractApiContext.registerApiContext(apiContext);
        if (apiContext != null) {
            apiContext.init();
        }
        try {
            Class.forName("com.nbos.capi.modules.identity.v0.IdentityIdsRegistry");
            Class.forName("com.nbos.capi.modules.media.v0.MediaIdsRegistry");

        } catch (Exception e) {
            System.out.println("unable to load");
        }
        TokenApiModel tokenApiModel = apiContext.getClientToken();
        if (tokenApiModel == null) {
            IdentityApi identityApi = IDS.getModuleApi("identity");
            identityApi.getClientToken();
        }
    }
}
