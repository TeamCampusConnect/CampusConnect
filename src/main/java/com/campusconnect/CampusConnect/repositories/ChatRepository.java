package com.campusconnect.CampusConnect.repositories;

import com.campusconnect.CampusConnect.entity.ChatEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, ObjectId> {
    Optional<ChatEntity> findBySenderAndReceiver(ObjectId sender, ObjectId receiver);
}
