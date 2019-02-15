package application.utils;

import javafx.scene.control.Tooltip;

public class UIUtils {


    public static Tooltip getTooltip(String content) {
        return new Tooltip(content);
    }
}
