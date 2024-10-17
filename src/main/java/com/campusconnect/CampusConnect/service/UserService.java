package com.campusconnect.CampusConnect.service;
import com.campusconnect.CampusConnect.dto.PostDTO;
import com.campusconnect.CampusConnect.entity.PostEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<PostDTO> getAllPosts(ObjectId userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getPosts().stream()
                .map(this::PostObjToDTOMapping)
                .collect(Collectors.toList());
    }

    private PostDTO PostObjToDTOMapping(PostEntity postData) {
        return new PostDTO(
                postData.getId(),
                postData.getUsersId(),
                postData.getUserName(),
                postData.getTitle(),
                postData.getContent(),
                postData.getImageUri());
    }

}
