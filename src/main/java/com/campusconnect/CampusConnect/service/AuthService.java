package com.campusconnect.CampusConnect.service;
import com.campusconnect.CampusConnect.dto.CommonDTO;
import com.campusconnect.CampusConnect.dto.UniversityDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.CompanyRepository;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivilegedExceptionAction;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public void userSignUp(@Valid UserDTO userData){
        try{
            UserEntity userEntity = (UserEntity) mapToEntity(userData);
            userRepository.save(userEntity);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void universitySignUp(@Valid UniversityDTO universityData) {
        try {
            UniversityEntity universityEntity = (UniversityEntity) mapToEntity(universityData);
            universityRepository.save(universityEntity);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private <T extends CommonDTO> Object mapToEntity(T dto){
        if(dto instanceof UserDTO userDTO){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setUserName(userDTO.getUserName());
            userEntity.setNameOfUniversity(userDTO.getNameOfUniversity());
            userEntity.setUniversityReg(userDTO.getUniversityReg());
            userEntity.setCourse(userDTO.getCourse());
            userEntity.setBranch(userDTO.getBranch());
            userEntity.setCurrentCompany(userDTO.getCurrentCompany());
            userEntity.setPlacementStatement(userDTO.getPlacementStatement());
            return userEntity;
        }
        else if(dto instanceof UniversityEntity universityDTO){
            UniversityEntity universityEntity = new UniversityEntity();
            universityEntity.setEmail(universityDTO.getEmail());
            universityEntity.setPassword(universityDTO.getPassword());
            universityEntity.setNameOfUniversity(universityDTO.getNameOfUniversity());
            universityEntity.setUniversityId(universityDTO.getUniversityId());
            universityEntity.setOfficerHead(universityDTO.getOfficerHead());
            universityEntity.setEstablishedIn(universityDTO.getEstablishedIn());
            universityEntity.setNoOfCompanyVisit(universityDTO.getNoOfCompanyVisit());
            universityEntity.setNirfRanking(universityDTO.getNirfRanking());
            universityEntity.setLocationOfUniversity(universityDTO.getLocationOfUniversity());
            universityEntity.setAllStudents(universityDTO.getAllStudents());
            universityEntity.setCompanyList(universityDTO.getCompanyList());
            return universityEntity;
        }
        throw new IllegalArgumentException("Unsupported DTO type");
    }




}
