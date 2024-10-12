    package com.campusconnect.CampusConnect.dto;

    import jakarta.validation.constraints.NotNull;
    import lombok.Data;
    import org.bson.types.ObjectId;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.index.Indexed;

    @Data
    public class UniversityNameListDTO {

        @Id
        private ObjectId id;

        @Indexed
        @NotNull(message = "university name cannot be empty")
        private String nameOfUniversity;

    }
