package com.nbos.capi.api.v0;

import okhttp3.Call;
import okhttp3.Response;
/**
 * Created by vivekkiran on 6/25/16.
 */

public interface NetworkCallback {
    void onResponse(Call call, Response response);

    void onFailure(Call call, Throwable t);

}
