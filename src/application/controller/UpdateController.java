package application.controller;

import java.util.HashMap;

import application.bean.BaseResponse;
import application.config.Config;
import application.http.CallBack;
import application.http.RealHttpUtils;

public class UpdateController {


	public static void checkVersion() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("currentVersion", Config.APP_VERSION);
		hashMap.put("currentCode", Config.APP_VERSION_CODE);
		RealHttpUtils.getInstance().postJson(Config.BASE_URL + "/apkInfo/getCordovaVersion", hashMap, new CallBack() {

			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}

			@Override
			public void onError(int errorCode, String errorMsg) {
				System.out.println(errorCode+"........."+ errorMsg);
			}
		});
	}
}
