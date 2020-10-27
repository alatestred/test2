package com.servlet.service;

import com.servlet.domain.Chat;
import com.servlet.domain.enams.ChatType;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    public static List<Chat> getChats(long id) throws Exception{
        DBConnectionService connectionService = new DBConnectionService();
        Connection connection = connectionService.getConnection();
        String sqlReq = "select c.id, c.type, case when c.type = 'SINGLE' then (select name from users where  " +
                "id = uc.user_id) " +
                "when c.type = 'GROUP' then c.name end " +
                "from chat as c inner join user_chat uc on c.id = uc.chat_id where uc.user_id = 1 ";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlReq);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Chat> chatList = new ArrayList<>();
        while (resultSet.next()) {
            Chat chat = new Chat();

            chat.setId(resultSet.getLong(1));
            chat.setType(ChatType.valueOf(resultSet.getString(2)));
            chat.setName(resultSet.getString(3));
            chatList.add(chat);
        }

       return chatList;
    }
}
