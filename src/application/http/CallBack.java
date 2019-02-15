package application.http;

/**
 * 先不实现泛型
 *
 * @param
 */
public interface CallBack {


    void onSuccess(String result);

    void onError(int errorCode, String errorMsg);
}
