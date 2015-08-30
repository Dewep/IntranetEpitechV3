package net.dewep.intranetepitech.api;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;

import net.dewep.intranetepitech.MyApplication;

public class API {
    private static final String BASE_URL = "https://intra.epitech.eu";

    public static Builders.Any.B request(String method, String url) {
        return Ion.with(MyApplication.getAppContext()).load(method.toUpperCase(), url);
    }

    public static Builders.Any.B get(String url) {
        return request("GET", url);
    }

    public static Builders.Any.F post(String url, JsonObject param) {
        return request("POST", url).setJsonObjectBody(param);
    }
}
