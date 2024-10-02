package com.campusconnect.CampusConnect.entity;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "University")
public class UniversityEntity {

    private ObjectId id;

    @Id
    @Indexed(unique = true)
    @NonNull
    private String email;
    private String password;
//  Name of the university
    @Indexed
    @NonNull
    private String nameOfUniversity;
//  placement officers head name
    private String officerHead;
//  Collage was founded in
    private Date establishedIn;
//  number of companies visit on an avg. year
    private int noOfCompanyVisit;
//  ranking of the collage
    private int nirfRanking;
//  location of the university
    private String locationOfUniversity;
// list of students registered in that university name.
    private List<UserEntity> allStudents;
//  List of the company's that comes to the campus for hiring.
    private List<CompanyEntity> companyList;

}
