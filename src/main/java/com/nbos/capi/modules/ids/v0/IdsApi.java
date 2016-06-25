package com.nbos.capi.modules.ids.v0;

import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.ApiContext;
import com.nbos.capi.api.v0.NetworkApi;

/**
 * Created by vivekkiran on 6/23/16.
 */

public class IdsApi extends NetworkApi {
    public IdsApi(){
        setHost(AbstractApiContext.getApiContext().getHost());
    }
    // the wrapper methods to call IdsRemoteApi
    public IdsApi(String host) {
        setHost(host);
        setRemoteApiClass(IdsRemoteApi.class);
    }
}

/*
In Idn lib
1. IdentityApi
2. IdentityRemoteApi
3. IdnSDK.init( ApiContext ) { }

In Android lib:
3. AndroidApiContext extends InMemoryApiContext

In Android App
4. c = new AndroidApiContext(); IdnSDK.init(c)
5. IdsApi idsApi = new IdsApi()
6. IdentityAPi identityApi = new IdentityApi()

------
IDS.getModule('identity')

5. IdsApi idsApi = IDS.getModule('identity')

 */