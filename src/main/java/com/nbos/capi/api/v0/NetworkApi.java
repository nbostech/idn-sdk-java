package com.nbos.capi.api.v0;

import java.io.IOException;

import io.swagger.models.Swagger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivekkiran on 6/14/16.
 */
/**
 *    -
 *    com.nbos.com.nbos.capi.modules.ids.v0
 IdsApi extends NetworkApi
 IdsRemoteApi
 -- necessary Ids models --
 */

/**
 * API associated with a module
 */
public class NetworkApi {

    String moduleName;
    String host;
    Swagger sw;

    Class<?> remoteApiClass;
    Object remoteApi;

    public NetworkApi() {

    }
    public NetworkApi(String host, Class<?> remoteApiClass) {
        this.host = host;
        this.remoteApiClass = remoteApiClass;
    }

    public <T> T getRemoteApi() {
        if(remoteApi !=null ) {
            return (T)remoteApi;
        }
        try {
            remoteApi = getRetrofitClient().create(remoteApiClass);
            return (T)remoteApi;
        } catch( Exception x ) {
            //  Log.i("IDS","unable to instantiate new object");
        }
        return null;
    }

    public void setHost(String host) {
        this.host=host;
        if(host.endsWith("/")) {
            this.host = host.substring(0,host.length()-1);
        }
    }
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getHost() {
        return host;
    }

    public Swagger getSw() {
        return sw;
    }

    public void setSw(Swagger sw) {
        this.sw = sw;
    }
    public Class<?> getRemoteApiClass() {
        return remoteApiClass;
    }

    public void setRemoteApiClass(Class<?> remoteApiClass) {
        this.remoteApiClass = remoteApiClass;
    }
    public Request.Builder newRequest(String api) {
        String endpoint = api;
        if(!api.startsWith("http")) {
            endpoint = host+api;
        }
        return new Request.Builder().url(endpoint);
    }

    public void get(Request request, final NetworkCallback callback) {
        OkHttpClient client = getOkHttpClient();
        Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onFailure(call,e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callback.onResponse(call,response);
                }
            });

    }

    protected Retrofit getRetrofitClient(){
        // TODO: get the host based on swagger
        String host = AbstractApiContext.get().getHost(moduleName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    protected OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }


    public Response post(Request request) {
        return null;
    }

    public Response put(Request request) {
        return null;
    }
}
