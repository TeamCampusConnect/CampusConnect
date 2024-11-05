package com.campusconnect.CampusConnect.dto;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ChatDTO {

    private ObjectId id;
    private ObjectId sender;
    private ObjectId receiver;
    private String  receiverName;

    @Override
    public String toString() {
        return "ChatDTO{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }
}
