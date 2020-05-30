package com.senjoeson.cordovahelp.http;

import java.io.IOException;

import javafx.application.Platform;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkHttpCallBack implements Callback {

    private CallBack callBack;

    public OkHttpCallBack(CallBack callback) {
        this.callBack = callback;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        try {
            callBack.onError(call.execute().code(), e.getMessage());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String content = response.body().string();
        if (response.code() == 200) {
            System.out.println("call = " + content);
            Platform.runLater(() -> callBack.onSuccess(content));

        } else {
            Platform.runLater(() -> callBack.onError(response.code(), call.request().toString()));

        }

    }
}
