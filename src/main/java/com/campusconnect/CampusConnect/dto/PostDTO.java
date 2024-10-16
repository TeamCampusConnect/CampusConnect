package com.campusconnect.CampusConnect.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PostDTO {

    @NotNull
    private String title;

    private ObjectId userId;

    private String userName;

    private String content;

    private String imageUri;



}
