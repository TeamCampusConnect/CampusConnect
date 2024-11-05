package com.campusconnect.CampusConnect.entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "message")
@Data
public class MessageEntity {
    @Id
    private ObjectId id;
    @NotNull
    private ObjectId sender;
    @NotNull
    private ObjectId receiver;
    @NotNull
    private String content;
    private LocalDateTime timestamp;

}
