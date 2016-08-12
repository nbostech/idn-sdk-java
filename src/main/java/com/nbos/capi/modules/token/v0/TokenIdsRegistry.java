package com.nbos.capi.modules.token.v0;

import com.nbos.capi.api.v0.IdnModule;
import com.nbos.capi.modules.ids.v0.IDS;

/**
 * Created by vivekkiran on 8/11/16.
 */
@IdnModule(moduleName = "token")
public class TokenIdsRegistry {
    static {
        IDS.register("token", TokenApi.class);
    }
}
