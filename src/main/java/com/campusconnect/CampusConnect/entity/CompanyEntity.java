package com.campusconnect.CampusConnect.entity;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashMap;
import java.util.List;

@Document(collection =  "Company")
public class CompanyEntity {

    @Id
    private ObjectId id;

//    Company name
    @NonNull
    private String companyName;

    // Contains a list of names of university with a list of students in the company with the students.
    private HashMap<String, List<ObjectId>> campusToStudentsMap;


}
