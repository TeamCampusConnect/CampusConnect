package com.campusconnect.CampusConnect.entity;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserEntity {


    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String email;

    private String password;

    @NonNull
    private String userName;

    @NonNull
    private String nameOfUniversity;

    private long universityReg;

    private String course;

    private String Branch;

    private String currentCompany;

    @NonNull
    private String placementStatement;

}
