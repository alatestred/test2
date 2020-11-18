package com.servlet.service;

import com.servlet.domain.User;
import com.servlet.domain.dto.UserDTO;
import com.servlet.repositories.UserRepository;

import javax.ejb.EJB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to connect with DB
 * @author Daulet Zholtayev
 * @since 25.09.2020 - 20:06
 */
public class UserService {
//
//    @EJB
//    UserRepository userRepository;

    public static void createUser(String login, String password, String name) throws Exception {
        if (login == null || password == null || name == null) {
            throw new Exception("Invalid fields");
        }
        if (findUser(login) != null) {
            throw new Exception("Login exists");
        }

        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();

        String sql = "insert into users (name, login, password) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, password);
        preparedStatement.execute();
    }

    public static List<UserDTO> findUsersLikeLogin(String pretty, Long ownerId) throws SQLException, ClassNotFoundException {
        pretty = "%" + pretty +  "%";

        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();

        String sql = "select * from users where login like ? and id!= ? limit 10";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, pretty);
        preparedStatement.setLong(2, ownerId);


        List<UserDTO> result = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
           result.add(new UserDTO(resultSet.getLong("id"),
                   resultSet.getString("login"),
                   resultSet.getString("name")));
        }
        return result;
    }

    public static User findUser(String login) throws SQLException, ClassNotFoundException {
//        DBConnectionService connectionService = new DBConnectionService();
//        Connection connection = connectionService.getConnection();
//
//        String sql = "select * from users where login=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, login);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if(resultSet.next()) {
//            return new User(resultSet.getLong("id"),
//                    resultSet.getString("login"),
//                    resultSet.getString("name"),
//                    resultSet.getString("password"));
//        }
        UserRepository userRepository = new UserRepository();
        return userRepository.findByLogin(login);
    }
}
