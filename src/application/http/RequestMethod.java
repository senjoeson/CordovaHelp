package application.http;

import java.util.Map;

/**
 * 作为一个请求方法 我们需要的是什么 参数和地址,当然还需要数据回调
 */
public interface RequestMethod {


     void get(String url, Map<String, Object> params, CallBack callBack);

     void post(String url, Map<String, Object> params, CallBack callBack);

     void postJson(String url, Map<String, Object> params, CallBack callBack);
}
