package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.DtoConverstion.DtoConverterHelper;
import com.campusconnect.CampusConnect.dto.ChatDTO;
import com.campusconnect.CampusConnect.entity.ChatEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.exception.EntityNotFoundException;
import com.campusconnect.CampusConnect.repositories.ChatRepository;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class MessagingService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final DtoConverterHelper dtoConverterHelper;


    MessagingService(UserRepository userRepository, ChatRepository chatRepository, DtoConverterHelper dtoConverterHelper) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.dtoConverterHelper = dtoConverterHelper;
    }

    public List<ChatDTO> getAllChats(ObjectId userId) {

        return Collections.emptyList();
    }

    @Transactional
    public ChatDTO createChatOrGetChat(ObjectId sender, ObjectId receiver) {
        try {
            // Find sender and receiver entities
            UserEntity senderEntity = userRepository.findById(sender)
                    .orElseThrow(() -> new EntityNotFoundException("Sender was not found in the db."));
            UserEntity receiverEntity = userRepository.findById(receiver)
                    .orElseThrow(() -> new EntityNotFoundException("Receiver was not found in the db."));

            // Check if a chat already exists between sender and receiver
            Optional<ChatEntity> existingChat = chatRepository.findBySenderAndReceiver(sender, receiver);
            if (existingChat.isEmpty()) {
                existingChat = chatRepository.findBySenderAndReceiver(receiver, sender);
            }

            if (existingChat.isPresent()) {
                // Chat exists, return the DTO
                return dtoConverterHelper.chatEntityToDto(existingChat.get());
            }

            // If no existing chat, create a new one
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setSender(sender);
            chatEntity.setReceiver(receiver);
            chatRepository.save(chatEntity);

            // Add the chat to both users’ allChats map
            senderEntity.getAllChats().put(receiver, chatEntity);
            receiverEntity.getAllChats().put(sender, chatEntity);

            // Save updates to user entities
            userRepository.save(senderEntity);
            userRepository.save(receiverEntity);

            return dtoConverterHelper.chatEntityToDto(chatEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
