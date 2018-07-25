import application.utils.FileUtils;
import application.utils.PathUtils;

public class TestFileExist {

    public static void main(String[] args) {
        new Thread(() -> {
            boolean fileExist = FileUtils.fileExist("C:\\Users\\senjoeson\\Desktop\\plugins\\TestPlugin\\src\\android");
            System.out.println(fileExist);
        }).start();

        System.out.println(PathUtils.getSrcPath("C:\\Users\\senjoeson\\Desktop\\plugins\\TestPlugin", "android"));

    }


}
