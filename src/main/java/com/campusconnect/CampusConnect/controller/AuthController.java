package com.campusconnect.CampusConnect.controller;

import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


//  Signup request
    @PostMapping("/user/signup")
    public void userSignUp(@Valid @RequestBody UserDTO userData){
         try{
             authService.userSignUp(userData);
         }
         catch (Exception e){
             System.out.println(e);
         }
    }

    @PostMapping("/university/signup")
    public void universitySignUp(@Valid @RequestBody UniversityEntity university){
         try{

         }
         catch (Exception e){
             System.out.println(e);
         }
    }

}


// The auth controller will handel all the auth related requests.