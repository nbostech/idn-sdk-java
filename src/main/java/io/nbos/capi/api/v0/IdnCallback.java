package io.nbos.capi.api.v0;

import retrofit2.Response;

/**
 * Created by vineeln on 6/25/16.
 */
public interface IdnCallback<T> {

    void onResponse(Response<T> response);

    void onFailure(Throwable t);

}
