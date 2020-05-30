package com.senjoeson.cordovahelp.http;


import java.util.Map;

public class RealHttpUtils implements RequestMethod {
    private static RealHttpUtils instance;
    public RealHttpUtils() {
    }

    public static RealHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RealHttpUtils.class) {
                if (instance == null) {
                    instance = new RealHttpUtils();
                }
            }
        }
        return instance;
    }

    @Override
    public void get(String url, Map<String, Object> params, CallBack callBack) {
        proxyCenter.get(url, params, callBack);
    }

    @Override
    public void post(String url, Map<String, Object> params, CallBack callBack) {
        proxyCenter.post(url, params, callBack);
    }

    @Override
    public void postJson(String url, Map<String, Object> params, CallBack callBack) {
        proxyCenter.postJson(url, params, callBack);
    }

    private static IProxyCenter proxyCenter;

    public static void chooseProxy(IProxyCenter iProxyCenter) {
        proxyCenter = iProxyCenter;
    }


}