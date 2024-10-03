package com.campusconnect.CampusConnect.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class UserEntity {

    @Id
    private ObjectId id;

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

    private String placementStatement;


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", nameOfUniversity='" + nameOfUniversity + '\'' +
                ", universityReg=" + universityReg +
                ", course='" + course + '\'' +
                ", Branch='" + Branch + '\'' +
                ", currentCompany='" + currentCompany + '\'' +
                ", placementStatement='" + placementStatement + '\'' +
                '}';
    }
}
