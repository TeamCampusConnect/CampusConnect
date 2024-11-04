package com.campusconnect.CampusConnect.entity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "messages")
public class MessageEntity {
    @Id
    private ObjectId id;

    @Field("sender")
    private ObjectId sender;

    @Field("receiver")
    private ObjectId receiver;

    @Field("content")
    private String content;

    @Field("timestamp")
    private LocalDateTime timestamp;

}
