package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.dto.LoginDTO;
import com.campusconnect.CampusConnect.dto.UniversityDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    AuthService(UserRepository userRepository, UniversityRepository universityRepository) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
    }

    @Transactional
    public void userSignUp(@Valid UserDTO userData) {
        // Map UserDTO to UserEntity
        UserEntity userEntity = mapToEntity(userData);

         // Fetch the university by the provided ID
        Optional<UniversityEntity> universityOptional = universityRepository.findById(userData.getUniversityId());
        // Check if university is present
        if (universityOptional.isPresent()) {
            UniversityEntity university = universityOptional.get();
            System.out.println(university.toString());

            // Add the student's ID to the university's student list
            List<UserEntity> allStudents = university.getAllStudents();
            if (allStudents != null) {
                allStudents.add(userEntity);
            } else {
                // Initialize the list if it's null
                allStudents = new ArrayList<>();
                allStudents.add(userEntity);
                university.setAllStudents(allStudents);
            }

            // Save the user and update the university entity
            userRepository.save(userEntity);
            universityRepository.save(university);
        } else {
            throw new RuntimeException("University not found with id: " + userData.getUniversityId());
        }
    }

    public boolean userLogin(@Valid LoginDTO userData) {
        Optional<UserEntity> user = userRepository.findByEmail(userData.getEmail());

        if (user.isEmpty()) {
            return false;
        }
        return user.get().getPassword().equals(userData.getPassword());
    }

    // University login
    public boolean universityLogin(@Valid LoginDTO universityData) {
        Optional<UniversityEntity> university = universityRepository.findByEmail(universityData.getEmail());

        if (university.isEmpty()) {
            return false;
        }

        return university.get().getPassword().equals(universityData.getPassword());
    }

    @Transactional
    public void universitySignUp(@Valid UniversityDTO universityData) {
        UniversityEntity universityEntity = mapToEntity(universityData);
        universityRepository.save(universityEntity);
    }

    private UserEntity mapToEntity(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setUniversityId(dto.getUniversityId());
        userEntity.setPassword(dto.getPassword());
        userEntity.setUserName(dto.getUserName());
        userEntity.setNameOfUniversity(dto.getNameOfUniversity());
        userEntity.setUniversityReg(dto.getUniversityReg());
        userEntity.setCourse(dto.getCourse());
        userEntity.setBranch(dto.getBranch());
        userEntity.setCurrentCompany(dto.getCurrentCompany());
        userEntity.setPlacementStatement(dto.getPlacementStatement());
        return userEntity;
    }

    private UniversityEntity mapToEntity(UniversityDTO dto) {
        UniversityEntity universityEntity = new UniversityEntity();
        universityEntity.setEmail(dto.getEmail());
        universityEntity.setPassword(dto.getPassword());
        universityEntity.setNameOfUniversity(dto.getNameOfUniversity());
        universityEntity.setOfficerHead(dto.getOfficerHead());
        universityEntity.setEstablishedIn(dto.getEstablishedIn());
        universityEntity.setNoOfCompanyVisit(dto.getNoOfCompanyVisit());
        universityEntity.setNirfRanking(dto.getNirfRanking());
        universityEntity.setLocationOfUniversity(dto.getLocationOfUniversity());
        return universityEntity;
    }
}
