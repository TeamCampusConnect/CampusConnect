package com.campusconnect.CampusConnect.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "chat")
@Data
public class ChatEntity {
    @Id
    private ObjectId id;

    @NotNull
    private ObjectId sender;
    @NotNull
    private ObjectId receiver;
    @DBRef
    List<MessageEntity> messages = new ArrayList<>();

    public ChatEntity() {
    } public ChatEntity(ObjectId id, ObjectId id1) {
        this.sender=id;
        this.receiver=id1;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id=" + id +
                ", messages=" + messages +
                '}';
    }
}
