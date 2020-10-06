package com.servlet.service;

import com.servlet.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service to connect with DB
 * @author Daulet Zholtayev
 * @since 25.09.2020 - 20:06
 */
public class UserService {

    public static void createUser(String login, String psw, String name) throws Exception {
        if (login == null || psw == null || name == null) {
            throw new Exception("Invalid fields");
        }
        if (findUser(login) != null) {
            throw new Exception("Login exists");
        }

        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();

        String sql = "insert into \"user\"(name, login, psw) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, psw);
        preparedStatement.execute();
    }

    public static User findUser(String login) throws SQLException, ClassNotFoundException {
        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();

        String sql = "select * from \"user\" where login=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            return new User(resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("psw"));
        }
        return null;
    }
}
