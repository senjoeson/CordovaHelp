package com.senjoeson.cordovahelp.http;

import java.util.Map;

public interface IProxyCenter {
	void get(String url, Map<String, Object> params, CallBack callBack);

	void post(String url, Map<String, Object> params, CallBack callBack);

	void postJson(String url, Map<String, Object> params, CallBack callBack);
}
