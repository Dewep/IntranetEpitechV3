package net.dewep.intranetepitech.api;

import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Response;

import net.dewep.intranetepitech.MyApplication;

public abstract class ResponseIntranet<T> implements FutureCallback<Response<T>> {

    public Response<T> response = null;

    public FutureCallback<Response<T>> callback() {
        return this;
    }

    public void onCompleted(Exception e, Response<T> response_obj) {
        response = response_obj;
        if (e != null) {
            onError(e);
        } else if (response.getHeaders().code() < 200 || response.getHeaders().code() >= 300) {
            onError(response.getResult());
        } else {
            onSuccess(response.getResult());
        }
    }

    public abstract void onSuccess(T result);

    public abstract void onError(T result);

    public void onError(Exception e) {
        Toast.makeText(MyApplication.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

}