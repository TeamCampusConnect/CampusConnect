package com.campusconnect.CampusConnect.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private ObjectId id;

    @NotNull
    private String title;

    private ObjectId userId;

    private String userName;

    private String content;

    private String imageUri;

    private Date createdAt;


    public PostDTO(ObjectId id, ObjectId usersId, String userName, String title, String content, String imageUri, Date createdAt) {
        this.id = id;
        this.userId=usersId;
        this.userName=userName;
        this.title=title;
        this.content=content;
        this.imageUri=imageUri;
        this.createdAt=createdAt;
    }


}


/*
{
    "title":"",
    "userId":"",
    "userName":"",
    "content":"",
    "imageUri":""
}
* */