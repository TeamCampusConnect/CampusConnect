package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

 @Test
    public void testFindByUserName(){
       assertNotNull(userRepository.findByEmail("sultan@gmail.com"));
     }


}
