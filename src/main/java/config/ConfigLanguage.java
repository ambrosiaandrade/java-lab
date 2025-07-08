package config;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

public class ConfigLanguage {

    private static final String CONFIG_FILE = ".env";
    private static Locale locale;

    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            String profile = properties.getProperty("app.profile", "pt").toLowerCase();
            switch (profile) {
                case "en":
                    locale = Locale.forLanguageTag("en-US");
                    break;
                case "pt":
                default:
                    locale = Locale.forLanguageTag("pt-BR");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            locale = Locale.forLanguageTag("pt-BR");
            System.out.println("Failed to load configuration file. Defaulting to: " + locale);
        }
    }

    public static Locale getLocale() {
        return locale;
    }

}
