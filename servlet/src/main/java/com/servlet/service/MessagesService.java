package com.servlet.service;

import com.servlet.domain.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesService {

    public static List<Long> getUserIdsOfChat(Long chatId) throws SQLException, ClassNotFoundException {
        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();
        String sql = "select uc.user_id as user_id from user_chat uc where uc.chat_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, chatId);

        List<Long> result = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(resultSet.getLong("user_id"));
        }
        return result;
    }

    public static List<Message> getMessages(long chatId) throws SQLException, ClassNotFoundException {
        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();
        String sql = "select m.id, m.message, m.date, u.name  from messages m left join users as u on u.id=m.author where m.chat_id = ? order by m.date asc";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, chatId);
        List<Message> messageList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getLong("id"));
            message.setMessage(resultSet.getString("message"));
            message.setDate(resultSet.getTimestamp("date"));
            message.setAuthor(resultSet.getString("name"));
            messageList.add(message);
        }
        return messageList;
    }

    public static Message setMessage(String messageIn, long chatId, long author, String login) throws SQLException, ClassNotFoundException {
        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();
        String inSql = "insert into messages (message, date, chat_id, author) values (? , NOW(), ?, ? );";
        PreparedStatement preparedStatement = connection.prepareStatement(inSql);
        preparedStatement.setString(1, messageIn);
        preparedStatement.setLong(2, chatId);
        preparedStatement.setLong(3, author);
        preparedStatement.executeUpdate();

       if(login != null) {
           Message msg = new Message();
           msg.setAuthor(UserService.findUser(login).getName());
           msg.setChatId(chatId);
           msg.setDate(new Date());
           msg.setMessage(messageIn);
           return msg;
       } else {
           return null;
       }
    }
}
