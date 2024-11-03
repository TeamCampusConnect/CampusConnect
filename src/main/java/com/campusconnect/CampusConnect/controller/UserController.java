package com.campusconnect.CampusConnect.controller;

import com.campusconnect.CampusConnect.dto.PostDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.service.UserService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }


//  get all the post feed
    @PostMapping("/myPosts/{userId}")
    public ResponseEntity<?> getAllUsersPosts(@PathVariable ObjectId userId){
        try{
            List<PostDTO> usersPost = userService.getAllPosts(userId);
            return new ResponseEntity<>(usersPost,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/findByUserName/{universityId}/{userName}")
    public ResponseEntity<?> findByUserName(@Valid @PathVariable ObjectId universityId , @PathVariable String userName){
        try {
            String userNameToPass = userName.trim();
            List<UserDTO> userDTOS  = userService.findByUserName(universityId , userNameToPass);
            if(userDTOS != null){
                return new ResponseEntity<>( userDTOS , HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>(Collections.emptyList() ,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

