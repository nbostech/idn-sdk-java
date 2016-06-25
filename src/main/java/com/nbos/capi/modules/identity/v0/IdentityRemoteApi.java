package com.nbos.capi.modules.identity.v0;

import com.nbos.capi.api.v0.*;
import com.nbos.capi.api.v0.TokenApiModel;

import in.wavelabs.idn.DataModel.auth.SignUp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface IdentityRemoteApi {

    String baseIdentityUrl = "/api/identity/v0";
    String tokenUrl = "/oauth/token";
    String loginUrl = baseIdentityUrl + "/auth/login";
    String signupUrl = baseIdentityUrl + "/users/signup";

    @FormUrlEncoded
    @POST(tokenUrl)
    Call<TokenApiModel> getToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);

    @POST(loginUrl)
    Call<NewMemberApiModel> login(@Header("Authorization") String authorization, @Body LoginModel login);

    @POST(signupUrl)
    Call<NewMemberApiModel> signup(@Header("Authorization") String authorization, @Body MemberSignupModel signUpBody);

}
