package com.servlet.service;

import com.servlet.domain.Chat;
import com.servlet.domain.enams.ChatType;
import com.servlet.repositories.ChatRepository;

import java.util.Arrays;
import java.util.List;

public class ChatService {

    public static List<Chat> getChats(long id) {
        ChatRepository chatRepository = new ChatRepository();
        return chatRepository.getChats(id);
    }

    private static Long findExistsSingleChatId(Long currentId, Long userId) {
        ChatRepository chatRepository = new ChatRepository();
        return chatRepository.findExistsSingleChatId(currentId, userId);
    }

    public static Long createSingleChat(Long currentId, Long id) {
        Long existsChatId = findExistsSingleChatId(currentId, id);
        if (existsChatId != null) {
            return existsChatId;
        }
        return createSingleChat(Arrays.asList(currentId, id));
    }

    public static Long createGroupChat(String name, Long currentId, List<Long> ids) {
        ids.add(currentId);
        return createGroupChat(name, ids);
    }

    /*
     * Helpers
     */
    private static Long createSingleChat(List<Long> ids) {
        return createChat(null, ChatType.SINGLE, ids);
    }

    private static Long createGroupChat(String name, List<Long> ids) {
        return createChat(name, ChatType.GROUP, ids);
    }

    private static Long createChat(String name, ChatType type, List<Long> users) {
        ChatRepository chatRepository = new ChatRepository();
        Chat chat = new Chat(type, name);
        chatRepository.createAndFlush(chat);

        for (Long id : users) {
            createChatUser(id, chat.getId());
        }
        return chat.getId();
    }

    private static void createChatUser(Long userId, Long chatId) {
        ChatRepository chatRepository = new ChatRepository();
        chatRepository.addUserToChat(userId, chatId);
    }
}
