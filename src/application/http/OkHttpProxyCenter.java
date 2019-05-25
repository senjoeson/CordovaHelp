package application.http;

import com.google.gson.Gson;

import java.net.Proxy;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 采用代理模式 ----网络请求
 */
public class OkHttpProxyCenter implements IProxyCenter {


    private OkHttpClient okHttpClient;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public OkHttpProxyCenter() {
        okHttpClient = new OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(Duration.ofSeconds(60))
                .proxy(Proxy.NO_PROXY)
                .build();
    }


    @Override
    public void get(String url, Map<String, Object> params, CallBack callBack) {
        okHttpClient.newCall(createGetRequest(url, params)).enqueue(new OkHttpCallBack(callBack));
    }

    @Override
    public void post(String url, Map<String, Object> params, CallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, String.valueOf(params.get(key)));
        }
        getRequest(url, builder.build(), callBack);

    }

    @Override
    public void postJson(String url, Map<String, Object> params, CallBack callBack) {

        RequestBody body = RequestBody.create(JSON, new Gson().toJson(params));
        getRequest(url, body, callBack);
    }

    private void getRequest(String url, RequestBody body, CallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new OkHttpCallBack(callBack));
    }


    /**
     * 创建Get请求的Request
     *
     * @param url
     * @param params
     * @return 通过传入的参数返回一个Get类型的请求
     */
    public static Request createGetRequest(String url, Map<String, Object> params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                urlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }
}
