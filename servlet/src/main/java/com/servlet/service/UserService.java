package com.servlet.service;

import com.servlet.domain.User;
import com.servlet.repositories.UserRepository;

import java.sql.SQLException;
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
    UserRepository userRepository = new UserRepository();

    public static void createUser(String login, String password, String name) throws Exception {
        if (login == null || password == null || name == null) {
            throw new Exception("Invalid fields");
        }
        if (findUser(login) != null) {
            throw new Exception("Login exists");
        }

//        DBConnectionService connectionService = new DBConnectionService();
//        Connection connection = connectionService.getConnection();
//
//        String sql = "insert into users (name, login, password) values (?,?,?);";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, name);
//        preparedStatement.setString(2, login);
//        preparedStatement.setString(3, password);
//        preparedStatement.execute();

        UserRepository userRepository = new UserRepository();
        userRepository.createUser(login, password, name);
    }

    public static List<User> findUsersLikeLogin(String pretty, Long ownerId) {
        pretty = "%" + pretty + "%";
        UserRepository userRepository = new UserRepository();
        return userRepository.findUsersLikeLogin(pretty,ownerId);

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
