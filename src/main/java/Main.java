import com.senjoeson.cordovahelp.config.Config;
import com.senjoeson.cordovahelp.http.OkHttpProxyCenter;
import com.senjoeson.cordovahelp.http.RealHttpUtils;
import com.senjoeson.cordovahelp.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application implements Thread.UncaughtExceptionHandler {
    private final String[] classNameFilters = new String[]{"com.sun", "java."};       //过滤系统的类错误


    @Override
    public void init() throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(this);
        super.init();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        LogUtils.d("--------" + dateFormat.format(date) + "---------");
        LogUtils.d("程序入口初始化");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(Config.APP_NAME + Config.APP_VERSION);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/main.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.getIcons().add(new Image("drawable/cordova_bot.png"));
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 820, 711);
        scene.getStylesheets().add(getClass().getResource("css/MainStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
        RealHttpUtils.chooseProxy(new OkHttpProxyCenter());
      //  showCheckEnv();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(0,"sss");


    }

    /**
     *检测环境
     * @throws java.io.IOException
     */
    private void showCheckEnv() throws java.io.IOException {
        //创建一个检测环境
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/conf_environment.fxml"));
        Parent load = loader.load();
        Scene configScene = new Scene(load,600 ,400);
        stage.setScene(configScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StringBuilder builder = new StringBuilder();
        builder.append("Exception in thread ");
        builder.append("\"");
        builder.append(t.getName());
        builder.append("\"");
        builder.append("\t");
        builder.append(e.getClass().getName());
        builder.append("\t");
        builder.append(e.getMessage());
        builder.append("\r\n");

        builder.append(getTraceDetail(e.getStackTrace(), classNameFilters));

        Throwable throwable = e.getCause();
        while (throwable != null) {
            builder.append("Caused by: ");
            builder.append(throwable.getClass().getName());
            String message = throwable.getMessage();
            if (message != null) {
                builder.append(" ");
                builder.append(throwable.getMessage());
            }
            builder.append("\r\n");
            builder.append(getTraceDetail(throwable.getStackTrace(), classNameFilters));
            throwable = throwable.getCause();
        }
        System.err.println(builder.toString());
    }

    private String getTraceDetail(StackTraceElement[] elements, String... filters) {
        StringBuilder builder = new StringBuilder();
        elements:
        for (StackTraceElement element : elements) {
            String className = element.getClassName();

            for (String filter : filters) {
                if (className.contains(filter)) {
                    continue elements;
                }
            }
            builder.append("\t");
            builder.append("at");
            builder.append(" ");
            builder.append(element.getClassName());
            builder.append(".");
            builder.append(element.getMethodName());
            builder.append("(");
            builder.append(element.getFileName());
            builder.append(":");
            builder.append(element.getLineNumber());
            builder.append(")");
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
