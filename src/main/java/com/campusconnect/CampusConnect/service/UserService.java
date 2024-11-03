package com.campusconnect.CampusConnect.service;
import com.campusconnect.CampusConnect.DtoConverstion.DtoConverterHelper;
import com.campusconnect.CampusConnect.dto.PostDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.PostEntity;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
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
    private final UniversityRepository universityRepository;

    private final DtoConverterHelper dtoConverterHelper;

    public UserService(UserRepository userRepository,UniversityRepository universityRepository,DtoConverterHelper dtoConverterHelper){
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.dtoConverterHelper=dtoConverterHelper;
    }

    public List<PostDTO> getAllPosts(ObjectId userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getPosts().stream()
                .map(dtoConverterHelper::PostObjToDTOMapping)
                .collect(Collectors.toList());
    }

// TODO : make the opposit converter function convert form Entity to DTO
    public List<UserDTO> findByUserName(ObjectId universityId , String userName){
        Optional<UniversityEntity> university = universityRepository.findById(universityId);
        return university.map(universityEntity -> universityEntity
                .getAllStudents()
                .stream()
                .filter(student -> (student.getUserName().equals(userName)))
                .map(student -> new UserDTO())
                .collect(Collectors.toList())).orElseGet(List::of);
    }




}
