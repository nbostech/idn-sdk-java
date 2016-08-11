package com.nbos.capi.modules.token.v0;

import com.nbos.capi.api.v0.models.TokenApiModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by vivekkiran on 8/11/16.
 */

public interface TokenRemoteApi {
    String tokenUrl = "/oauth/token";

    @FormUrlEncoded
    @POST(tokenUrl)
    Call<TokenApiModel> getToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);

    @POST(tokenUrl)
    Call<TokenApiModel> refreshAccessToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType, @Field("refresh_token") String refreshToken);

}
