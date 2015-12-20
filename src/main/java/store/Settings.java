package store;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Sergey on 19.12.2015.
 */
public class Settings {
    private static Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    public static Settings getInstance() {
        return INSTANCE;
    }

    private Settings() {
        try{
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("jdbc.properties").getFile()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String value (String key) {
        return properties.getProperty(key);
    }
}
