package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public void createUser(UserEntity newUser){
        try{
            System.out.println("Saving user: " + newUser.toString());
            userRepo.save(newUser);
            System.out.println("User saved successfully");
        }
        catch (Exception e){
            System.out.println("Error saving user: " + e);
        }
    }



}
