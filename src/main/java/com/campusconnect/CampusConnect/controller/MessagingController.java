package com.campusconnect.CampusConnect.controller;

import com.campusconnect.CampusConnect.DtoConverstion.DtoConverterHelper;
import com.campusconnect.CampusConnect.dto.ChatDTO;
import com.campusconnect.CampusConnect.entity.ChatEntity;
import com.campusconnect.CampusConnect.service.MessagingService;
import com.mongodb.client.model.geojson.LineString;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
public class MessagingController {

    private final MessagingService messagingService;

    MessagingController(MessagingService messagingService){
        this.messagingService = messagingService;
     }

   @PostMapping("/allChatsList/{userId}")
   public ResponseEntity<?> getAllChatsWith(@Valid @PathVariable ObjectId userId){
        List<ChatDTO> chats = messagingService.getAllChats(userId);
        if(chats.isEmpty()){
            return new ResponseEntity<>( Collections.emptyList() , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( chats , HttpStatus.FOUND);
   }

    @PostMapping("/createChat/{sender}/{receiver}")
    public ResponseEntity<?> createChatInterface(@Valid @PathVariable ObjectId sender , @PathVariable ObjectId receiver){
        ChatDTO chatDTO = messagingService.createChatOrGetChat(sender,receiver);
        return new ResponseEntity<>(chatDTO,HttpStatus.CREATED);
    }


}
