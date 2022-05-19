package ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties prop = new Properties();

    static {
        try(InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String nameProperty) {
        return prop.getProperty(nameProperty);
    }
}
