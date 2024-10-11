package com.campusconnect.CampusConnect.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
@Data
public class UserEntity {

    @Id
    private ObjectId id;

    @NotNull(message = "email cannot be empty")
    @Email(message = "invalid email format")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "user name cannot be null")
    private String userName;

    @NotNull(message = "university name cannot be null")
    @Indexed
    private String nameOfUniversity;

    private long universityReg;

    private String course;

    private String branch;

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
                ", Branch='" + branch + '\'' +
                ", currentCompany='" + currentCompany + '\'' +
                ", placementStatement='" + placementStatement + '\'' +
                '}';
    }
}
