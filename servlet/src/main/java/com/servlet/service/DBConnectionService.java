package com.servlet.service;

import com.servlet.utils.PropertyUtils;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Daulet Zholtayev
 * @since 29.09.2020 - 20:51
 */
public class DBConnectionService {

    private static Connection connection = null;

    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null) {
            DBConfig config = getConfig();

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(config.getHost(),
                    config.getUsername(), config.getPassword());
        }
        return connection;
    }

    private DBConfig getConfig() {
        Properties properties = PropertyUtils.getProperties();
        return new DBConfig(properties.getProperty("db.host"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password"));
    }

    public class DBConfig {
        private String host;
        private String username;
        private String password;

        public DBConfig(String host, String username, String password) {
            this.host = host;
            this.username = username;
            this.password = password;
        }

        public String getHost() {
            return host;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }


}
