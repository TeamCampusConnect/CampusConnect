package com.campusconnect.CampusConnect.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Posts")
@Getter
@Setter
public class PostEntity {

    @Id
    private ObjectId id;

    private ObjectId usersId;

    private String title;
    private String content;
    private Date timeStamp;
}
