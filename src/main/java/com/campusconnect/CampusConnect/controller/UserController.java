package com.campusconnect.CampusConnect.controller;

import com.campusconnect.CampusConnect.dto.PostDTO;
import com.campusconnect.CampusConnect.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//

}

// Functions to implement
// 1. Get all posts of the user
// 2. Users university post list
// 3. University lost and found (based on the year of the user).
// 4. search for company
// 5.
// 6.
// 7.
// 8.
// 9.
// 10.
