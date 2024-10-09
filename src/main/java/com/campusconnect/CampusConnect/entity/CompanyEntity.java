package com.campusconnect.CampusConnect.entity;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection =  "Company")
public class CompanyEntity {

    @Id
    private ObjectId id;

//    Company name
    @NonNull
    @Indexed
    private String companyName;

    private List<UniversityStudentMapping> campusToStudentsMap;



}
