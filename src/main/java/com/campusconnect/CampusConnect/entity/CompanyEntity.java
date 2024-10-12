package com.campusconnect.CampusConnect.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection =  "Company")
@Data
public class CompanyEntity {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed
    private String companyName;
    private List<UniversityStudentMapping> campusToStudentsMap;
}
