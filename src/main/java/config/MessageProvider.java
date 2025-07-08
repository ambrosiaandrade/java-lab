package config;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageProvider {

    private static ResourceBundle bundle;
    private static String moduleName;

    public static String get(String key) {
        Locale locale = ConfigLanguage.getLocale();
        String baseName = moduleName + ".i18n.messages"; // ex: "src.streams.i18n.messages"
        bundle = ResourceBundle.getBundle(baseName, locale);
        return bundle.getString(key);
    }

    public static void setModuleName(String moduleName) {
        MessageProvider.moduleName = moduleName;
    }

    public static String getModuleName() {
        return moduleName;
    }

}
