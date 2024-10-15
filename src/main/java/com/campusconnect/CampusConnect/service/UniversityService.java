package com.campusconnect.CampusConnect.service;
import com.campusconnect.CampusConnect.dto.CompanyDTO;
import com.campusconnect.CampusConnect.dto.UniversityDTO;
import com.campusconnect.CampusConnect.dto.UniversityNameListDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.CompanyEntity;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.CompanyRepository;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UniversityService  {

    private final UniversityRepository universityRepository;
    private final CompanyRepository companyEntity;
    UniversityService(UniversityRepository universityRepository,CompanyRepository companyEntity){
        this.universityRepository = universityRepository;
        this.companyEntity=companyEntity;
    }

    public List<UniversityNameListDTO> getAllUniversities() {
        return universityRepository.findAllNamesOfUniversity();
    }

    public List<UserDTO> findAllStudents(ObjectId universityId) {
        return universityRepository.findById(universityId)
                .map(university -> university.getAllStudents().stream()
                        .map(this::mapToUserDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Transactional
    public CompanyDTO createCompany(ObjectId universityId , CompanyDTO companyDetails){
        Optional<UniversityEntity> universityEntityOptional = universityRepository.findById(universityId);
        if (universityEntityOptional.isPresent()){
            UniversityEntity university = universityEntityOptional.get();
            CompanyEntity companyEntity1 = mapToCompanyEntity(companyDetails);
            CompanyEntity savedEntity = companyEntity.save(companyEntity1);
            university.getCompanyList().add(savedEntity);
            companyDetails.setId(savedEntity.getId());
            return companyDetails;
        }
        return null;
    }

    private UserDTO mapToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUniversityId(userEntity.getUniversityId());
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setBranch(userEntity.getBranch());
        userDTO.setUniversityReg(userDTO.getUniversityReg());
        userDTO.setCurrentCompany(userEntity.getCurrentCompany());
        return userDTO;
    }

    private CompanyEntity mapToCompanyEntity(CompanyDTO companyDetails){
        CompanyEntity companyEntity1 = new CompanyEntity();
        companyEntity1.setCompanyName(companyDetails.getCompanyName());
        companyEntity1.setUniversityId(companyDetails.getUniversityId());
        return companyEntity1;
    }


}
