package net.dewep.intranetepitech.api.request;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Response;

import net.dewep.intranetepitech.api.API;
import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.ResponseIntranet;

public abstract class LoginAPI extends ResponseIntranet<JsonObject> {

    public LoginAPI(String login, String password) {
        JsonObject json = new JsonObject();
        json.addProperty("login", login);
        json.addProperty("password", password);
        API.post(Configurations.getUrlLogin(), json).asJsonObject().withResponse().setCallback(this);
    }

}
