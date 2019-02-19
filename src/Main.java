import java.text.SimpleDateFormat;
import java.util.Date;

import application.config.Config;
import application.http.OkHttpProxyCenter;
import application.http.RealHttpUtils;
import application.utils.LogUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application implements Thread.UncaughtExceptionHandler {
    private String[] classNameFilters = new String[]{"com.sun", "java."};       //过滤系统的类错误


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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("res/layout/main.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.getIcons().add(new Image("res/drawable/cordova_bot.png"));
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 820, 711);
        scene.getStylesheets().add(getClass().getResource("res/css/MainStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
        RealHttpUtils.chooseProxy(new OkHttpProxyCenter());

        //创建一个检测环境
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("res/layout/conf_environment.fxml"));
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
