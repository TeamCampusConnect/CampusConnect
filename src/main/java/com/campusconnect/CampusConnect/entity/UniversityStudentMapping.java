package com.campusconnect.CampusConnect.entity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "universityStudentMapping")
@Data
public class UniversityStudentMapping {
    private ObjectId universityId;
    private List<ObjectId> studentIds;
}
