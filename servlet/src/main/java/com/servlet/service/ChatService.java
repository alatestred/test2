package com.servlet.service;

import com.servlet.domain.Chat;
import com.servlet.domain.enams.ChatType;
import com.servlet.repositories.ChatRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    public static List<Chat> getChats(long id) throws Exception{
//        DBConnectionService connectionService = new DBConnectionService();
//        Connection connection = connectionService.getConnection();
//        String sqlReq = "select c.id, c.type, case when c.type = 'SINGLE' then (" +
//                "    select name from users as u " +
//                "inner join user_chat u2 on u.id = u2.user_id where u.id != uc.user_id" +
//                "    and u2.chat_id = c.id) " +
//                "when c.type = 'GROUP' then c.name end\n" +
//                "from chat as c inner join user_chat uc on c.id = uc.chat_id where uc.user_id = ?; ";
//        PreparedStatement preparedStatement = connection.prepareStatement(sqlReq);
//        preparedStatement.setLong(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        List<Chat> chatList = new ArrayList<>();
//        while (resultSet.next()) {
//            Chat chat = new Chat();
//
//            chat.setId(resultSet.getLong(1));
//            chat.setType(ChatType.valueOf(resultSet.getString(2)));
//            chat.setName(resultSet.getString(3));
//            chatList.add(chat);
//        }

        ChatRepository chatRepository = new ChatRepository();
       return chatRepository.getChats(id);
    }


    private static Long findExistsChatId(Long currentId, Long userId) throws SQLException, ClassNotFoundException {
//        DBConnectionService connectionService = new DBConnectionService();
//        Connection connection = connectionService.getConnection();
//        String sql = "select count(*), chat_id " +
//                "from user_chat u JOIN chat c on c.id = u.chat_id " +
//                "where c.type='SINGLE' AND (u.user_id = ? OR u.user_id = ?) " +
//                "group by chat_id;";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setLong(1, currentId);
//        preparedStatement.setLong(2, userId);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            int count = resultSet.getInt(1);
//            long chatId = resultSet.getLong(2);
//            if(count  == 2) {
//                return chatId;
//            }
//        }
        ChatRepository chatRepository = new ChatRepository();
        chatRepository.findExistsChatId(currentId, userId);
        return null;
    }

    public static Long createChat(Long currentId, Long id) throws SQLException, ClassNotFoundException {
        Long existsChatId = findExistsChatId(currentId, id);
        if(existsChatId != null) {
            return existsChatId;
        }
        Long chatId = createChat(null, ChatType.SINGLE);
        if(chatId != null) {
            createChatUser(currentId, chatId);
            createChatUser(id, chatId);
        }
        return chatId;
    }

    private static Long createChat(String name, ChatType chatType) throws SQLException, ClassNotFoundException {
        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();
        String inSql = "insert into chat (name, type) values (?, ?) RETURNING ID;";
        PreparedStatement preparedStatement = connection.prepareStatement(inSql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, chatType.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
//        ???
        return resultSet.getLong(1);
    }


    private static void createChatUser(Long userId, Long chatId) throws SQLException, ClassNotFoundException {
//        DBConnectionService connectionService = new DBConnectionService();
//        Connection connection = connectionService.getConnection();
//        String inSql = "insert into user_chat (user_id, chat_id) values (?, ?);";
//        PreparedStatement preparedStatement = connection.prepareStatement(inSql);
//        preparedStatement.setLong(1, userId);
//        preparedStatement.setLong(2, chatId);
//        preparedStatement.executeUpdate();
        ChatRepository chatRepository = new ChatRepository();
        chatRepository.createChatUser(userId, chatId);
    }
}
