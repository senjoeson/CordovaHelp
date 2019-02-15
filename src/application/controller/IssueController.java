package application.controller;

import application.bean.BaseResponse;
import application.http.CallBack;
import application.http.RealHttpUtils;
import application.utils.LogUtils;
import application.utils.MessageUtils;
import application.utils.TextUtils;
import com.google.gson.Gson;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;

public class IssueController {


    public TextField title;
    public TextArea content;
    public TextField email;
    public Button submit;
    public ProgressIndicator mProgressIndicator;
    String BASE_URL = "http://118.24.241.167:8081/";
    // String BASE_URL = "http://localhost:8081/";

    public void submitIssue(MouseEvent mouseEvent) {

        if (title.getText().trim().isEmpty() ||
                content.getText().trim().isEmpty()) {
            MessageUtils.showMessage("反馈内容不能为空");
            return;
        }
        if (email.getText().trim().isEmpty()) {
            MessageUtils.showMessage("邮箱地址不能为空");
            return;
        } else if (!TextUtils.checkEmail(email.getText().trim())) {
            MessageUtils.showMessage("邮箱地址不合法");
            return;
        }

        mProgressIndicator.setVisible(true);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sendTo", email.getText().trim());
        hashMap.put("subject", title.getText().trim());
        hashMap.put("content", content.getText().trim());
        RealHttpUtils.getInstance().post(BASE_URL + "/mail/sendMail", hashMap, new CallBack() {
            @Override
            public void onSuccess(String result) {

                BaseResponse response = new Gson().fromJson(result, BaseResponse.class);
                if ("200".equals(response.getReturnCode())) {
                    title.clear();
                    content.clear();
                    email.clear();
                }
                MessageUtils.showMessage(response.getReturnMsg(), Alert.AlertType.INFORMATION);
                mProgressIndicator.setVisible(false);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                LogUtils.d("errorCode" + errorCode + "errorMsg:" + errorMsg);
                MessageUtils.showMessage(errorMsg, Alert.AlertType.ERROR);
                mProgressIndicator.setVisible(false);
            }
        });

    }
}
