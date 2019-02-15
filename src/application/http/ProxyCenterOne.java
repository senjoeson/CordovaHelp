package application.http;


/**
 * 采用httpclient请求方式
 */
public class ProxyCenterOne{} /*implements IProxyCenter {

    private CloseableHttpClient httpClient;

    public ProxyCenterOne() {
        httpClient = HttpClients.createDefault();
    }

    @Override
    public void get(String url, Map<String, Object> param, CallBack callBack) {
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, (String) param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpClient.execute(httpGet);
            processResponse(response, callBack);
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onError(response.getStatusLine().getStatusCode(), e.getMessage());

        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void post(String url, Map<String, Object> param, CallBack callBack) {
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Charset.defaultCharset());
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            processResponse(response, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void postJson(String url, Map<String, Object> param, CallBack callBack) {
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(new Gson().toJson(param), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            processResponse(response, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    *//**
     * 处理请求
     *
     * @param response
     * @param callBack
     *//*
    public void processResponse(CloseableHttpResponse response, CallBack callBack) {
        try {
            String resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            if (response.getStatusLine().getStatusCode() == 200) {
                callBack.onSuccess(resultString);
            } else {
                callBack.onError(response.getStatusLine().getStatusCode(), resultString);
            }
        } catch (IOException e) {
            callBack.onError(response.getStatusLine().getStatusCode(), e.getMessage());
        }
    }
}*/
