package com.campusconnect.CampusConnect.controller.ControllerImplemntation;

import com.campusconnect.CampusConnect.dto.UniversityNameListDTO;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.service.Implementation.UniversityService;
import com.campusconnect.CampusConnect.service.UniversityServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/university")
public class UniversityController {

    private final UniversityService universityService;
    UniversityController(UniversityService universityService){
        this.universityService = universityService;
    }

    @PostMapping("/universityList")
        public ResponseEntity<List<UniversityNameListDTO>> listOfUniversity(){
        List<UniversityNameListDTO> universityList = universityService.getAllUniversities();
        return ResponseEntity.ok(universityList);
    }

}

// List of functions to be implemented

// 1. Show the list of all the students
// 2. show the list of all the companies visited
// 3. Adding posts to the community that are visible to all the students of the university
// 4. Having the admin access that can delete users particular post
// 5. Uploading the list of students per company
// 6. Updating/editing own posts and deleting them.