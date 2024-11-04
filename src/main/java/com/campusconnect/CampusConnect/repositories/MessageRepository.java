package com.campusconnect.CampusConnect.repositories;

import com.campusconnect.CampusConnect.entity.MessageEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, ObjectId> {
}
