package com.campusconnect.CampusConnect.repositories;

import com.campusconnect.CampusConnect.entity.UniversityEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends MongoRepository<UniversityEntity, ObjectId> {
    Optional<UniversityEntity> findByEmail(String email);
}
