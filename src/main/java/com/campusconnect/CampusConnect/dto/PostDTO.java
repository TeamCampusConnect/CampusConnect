package com.campusconnect.CampusConnect.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDTO {

    @NotNull
    private String title;

    private String content;

    private String imageUri;



}
