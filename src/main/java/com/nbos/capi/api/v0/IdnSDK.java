package com.nbos.capi.api.v0;

import com.nbos.capi.api.v0.models.TokenApiModel;
import com.nbos.capi.modules.ids.v0.IDS;
import com.nbos.capi.modules.token.v0.TokenApi;

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

            Class.forName("com.nbos.capi.modules.token.v0.TokenIdsRegistry");

        } catch (Exception e) {
            System.out.println("unable to load");
        }
        TokenApiModel tokenApiModel = apiContext.getClientToken();
        if (tokenApiModel == null) {
            TokenApi tokenApi = IDS.getModuleApi("token");
            tokenApi.getClientToken();
        }
    }
}
