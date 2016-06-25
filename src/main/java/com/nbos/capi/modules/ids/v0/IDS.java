package com.nbos.capi.modules.ids.v0;


import com.nbos.capi.api.v0.NetworkApi;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.models.Swagger;
import io.swagger.parser.Swagger20Parser;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivekkiran on 6/14/16.
 */

public class IDS {


    // registry of module classes
    static Map registry = new HashMap();

    // registr of moduleApi Instance Objects
    static Map apiInstanceRegistry = new HashMap();

    static {
        try {
            Class.forName("in.wavelabs.idn.modules.identity.IdentityIdsRegistry");
        } catch( Exception x ) {
           // Log.i("IDS","unable to load class");
        }

        //registry.put("identity",NetworkApi.class);
    }


    public static List<?> findModules() {
        String[] modules = {"identity","core","media"};
        return Arrays.asList(modules);
    }
    public static List<?> findTenantModules(String tenantId) {
        String[] modules = {"identity","core","media"};
        return Arrays.asList(modules);
    }

    public static <Any> Any getIDSApi() {
        return (Any)getRetrofitClient().create(IdsApi.class);
    }

    public static <Any> Any getModuleApi(String moduleName) {
        System.out.println(registry);
        Class apiClass = (Class)registry.get(moduleName);
        if( apiClass == null ) {
            try {
                apiClass = Class.forName("com.nbos.capi.api.v0.NetworkApi");
                try {
                    NetworkApi api = (NetworkApi)apiClass.newInstance();
                    // TODO: we should get the host from IDS interface for the module
                    api.setHost("http://api.qa1.nbos.in/");
                    return (Any)api;
                } catch( Exception x ) {
                  //  Log.i("IDS","unable to instantiate new object");
                }
            } catch( Exception x ) {
               // Log.i("IDS","unable to load networkApi");
            }
        }
        if( apiClass != null ) {
            //getHostForModule(moduleName);
            try {
                try {
                    NetworkApi api = (NetworkApi)apiClass.newInstance();
                    return (Any)api;
                    // TODO: we should get the host from IDS interface for the module
                    //api.setHost("http://api.qa1.nbos.in/");
                } catch( Exception x ) {
                    //  Log.i("IDS","unable to instantiate new object");
                }


            } catch( Exception x ) {
              //  Log.i("IDS","unable to instantiate new object");
            }
        }
        return null;

    }
    public static String getHostForModule(String moduleName){
        //TODO: Get Host From Module Name
        final String[] host = new String[1];
        IdsRemoteApi idsRemoteApi = IDS.getIDSApi();
        idsRemoteApi.getModApiJson(moduleName).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               System.out.println(response);
                Swagger20Parser parser = new Swagger20Parser();
                try {
                    Swagger sw = parser.parse(response.body().string());
                    System.out.println(sw.getHost());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return host[0];
    }
    protected static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    protected static Retrofit getRetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.qa1.nbos.in/")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private static final HashMap<Class, Object> apiClients = new HashMap<>();



    public static void register(String moduleName, Class clazz){
        registry.put(moduleName,clazz);
    }


}
