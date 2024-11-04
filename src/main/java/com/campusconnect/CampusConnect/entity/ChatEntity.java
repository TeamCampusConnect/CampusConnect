package com.campusconnect.CampusConnect.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "chat")
public class ChatEntity {

    @Id
    @DBRef
    private ObjectId id;

    @DBRef
    List<MessageEntity> messages = new ArrayList<>();




}
