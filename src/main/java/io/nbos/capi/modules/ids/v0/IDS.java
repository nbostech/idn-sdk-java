package io.nbos.capi.modules.ids.v0;


import io.nbos.capi.api.v0.AbstractApiContext;
import io.nbos.capi.api.v0.ApiContext;
import io.nbos.capi.api.v0.NetworkApi;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by vivekkiran on 6/14/16.
 */

public class IDS {


    // registry of module classes
    private static Map<String,Class<?>> registry = new HashMap<>();

    // registr of moduleApi Instance Objects
    static Map apiInstanceRegistry = new HashMap();

    static {

        try {
            Class.forName("io.nbos.capi.modules.token.v0.TokenIdsRegistry");
        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    /**
     * returns networkApi with 'app' apiContext
     *
     * @param moduleName
     * @param <Any>
     * @return
     */
    public static <Any> Any getModuleApi(String moduleName) {
        return getModuleApi(moduleName, "app");
    }

    public static <Any> Any getModuleApi(String moduleName, String contextName) {
        System.out.println(registry);
        Class apiClass = (Class) registry.get(moduleName);
        if (apiClass == null) {
            try {
                apiClass = Class.forName("io.nbos.capi.api.v0.NetworkApi");
                NetworkApi api = (NetworkApi) apiClass.newInstance();
                ApiContext apiContext = AbstractApiContext.get(contextName);
                if (api != null) {
                    api.setApiContext(apiContext);
                    api.setHost(apiContext.getHost(moduleName));
                }
                return (Any) api;
            } catch (Exception x) {
                //  Log.i("IDS","unable to instantiate new object");
            }
        }
        if (apiClass != null) {
            try {
                NetworkApi api = (NetworkApi) apiClass.newInstance();
                ApiContext apiContext = AbstractApiContext.get(contextName);
                if (api != null) {
                    api.setApiContext(apiContext);
                    api.setHost(apiContext.getHost(moduleName));
                }
                return (Any) api;
            } catch (Exception x) {
                //  Log.i("IDS","unable to instantiate new object");
            }
        }
        return null;
    }


    public static void register(String moduleName, Class clazz) {
        registry.put(moduleName, clazz);
    }


}
