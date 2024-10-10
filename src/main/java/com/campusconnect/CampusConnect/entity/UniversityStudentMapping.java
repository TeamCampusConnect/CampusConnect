package com.campusconnect.CampusConnect.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "universityStudentMapping")
@Getter
@Setter
public class UniversityStudentMapping {
    private ObjectId universityId;
    private List<ObjectId> studentIds;
}
