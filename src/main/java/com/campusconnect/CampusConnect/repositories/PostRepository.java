package com.campusconnect.CampusConnect.repositories;

import com.campusconnect.CampusConnect.entity.PostEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<PostEntity, ObjectId> {
    // Find posts by university
    List<PostEntity> findByUniversityId(ObjectId universityId);
}

