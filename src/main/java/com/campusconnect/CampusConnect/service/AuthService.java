package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.dto.LoginDTO;
import com.campusconnect.CampusConnect.dto.UniversityDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversityRepository universityRepository;
    public void userSignUp(@Valid UserDTO userData) {
        UserEntity userEntity = mapToEntity(userData);
        userRepository.save(userEntity);
    }
    public boolean userLogin(@Valid LoginDTO userData) {
        Optional<UserEntity> user = userRepository.findByEmail(userData.getEmail());

        if (user.isEmpty()) {
            return false;
        }

        if (!user.get().getPassword().equals(userData.getPassword())) {
            return false;
        }

        return true;
    }

    // University login
    public boolean universityLogin(@Valid LoginDTO universityData) {
        Optional<UniversityEntity> university = universityRepository.findByEmail(universityData.getEmail());

        if (university.isEmpty()) {
            return false;
        }

        if (!university.get().getPassword().equals(universityData.getPassword())) {
            return false;
        }

        return true;
    }

     public void universitySignUp(@Valid UniversityDTO universityData) {
        UniversityEntity universityEntity = mapToEntity(universityData);
        universityRepository.save(universityEntity);
    }

     private UserEntity mapToEntity(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
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
