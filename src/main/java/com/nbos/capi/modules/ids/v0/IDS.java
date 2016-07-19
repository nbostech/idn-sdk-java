package com.nbos.capi.modules.ids.v0;


import com.nbos.capi.api.v0.AbstractApiContext;
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
            Class.forName("com.nbos.capi.modules.identity.v0.IdentityIdsRegistry");
            Class.forName("com.nbos.capi.modules.media.v0.MediaIdsRegistry");
        } catch( Exception x ) {
           // Log.i("IDS","unable to load class");
        }

        //registry.put("identity",NetworkApi.class);
    }

//
//    public static List<?> findModules() {
//        String[] modules = {"identity","core","media"};
//        return Arrays.asList(modules);
//    }
//    public static List<?> findTenantModules(String tenantId) {
//        String[] modules = {"identity","core","media"};
//        return Arrays.asList(modules);
//    }

    /**
     * returns networkApi with 'app' apiContext
     * @param moduleName
     * @param <Any>
     * @return
     */
    public static <Any> Any getModuleApi(String moduleName) {
        return getModuleApi(moduleName,"app");
    }

    public static <Any> Any getModuleApi(String moduleName, String contextName) {
        System.out.println(registry);
        Class apiClass = (Class)registry.get(moduleName);
        if( apiClass == null ) {
            try {
                apiClass = Class.forName("com.nbos.com.nbos.capi.api.v0.NetworkApi");
                NetworkApi api = (NetworkApi)apiClass.newInstance();
                if(api!=null) api.setApiContext(AbstractApiContext.get(contextName));
                return (Any)api;
            } catch( Exception x ) {
              //  Log.i("IDS","unable to instantiate new object");
            }
        }
        if( apiClass != null ) {
            try {
                NetworkApi api = (NetworkApi)apiClass.newInstance();
                if(api!=null) api.setApiContext(AbstractApiContext.get(contextName));
                return (Any)api;
            } catch( Exception x ) {
                //  Log.i("IDS","unable to instantiate new object");
            }
        }
        return null;
    }



    public static void register(String moduleName, Class clazz){
        registry.put(moduleName,clazz);
    }


}
