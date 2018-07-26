import application.LogUtils;
import application.config.Config;
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
        LogUtils.d("程序入口初始化");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("res/layout/main.fxml"));
        primaryStage.setTitle(Config.APP_NAME + Config.APP_VERSION);
        //Bounds layoutBounds = root.getLayoutBounds();
        primaryStage.getIcons().add(new Image("res/drawable/cordova_bot.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 820, 760));
        primaryStage.show();
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
