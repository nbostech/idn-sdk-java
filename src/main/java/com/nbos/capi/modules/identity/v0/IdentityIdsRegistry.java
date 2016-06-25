package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.modules.ids.v0.IDS;

import in.wavelabs.idn.modules.identity.IdentityApi;


/**
 * Created by vivekkiran on 6/16/16.
 */

public class IdentityIdsRegistry {
    static {
        IDS.register("identity",IdentityApi.class);
    }
}
