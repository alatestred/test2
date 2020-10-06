package com.servlet.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Daulet Zholtayev
 * @since 29.09.2020 - 20:48
 */
public class PropertyUtils {

    public static Properties getProperties() {
        InputStream stream = PropertyUtils.class.getClassLoader()
                .getResourceAsStream("db.properties");

        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
