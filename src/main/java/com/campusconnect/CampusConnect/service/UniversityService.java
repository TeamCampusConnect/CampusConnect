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
import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UniversityService  {

    private final UniversityRepository universityRepository;
    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;
    UniversityService(UniversityRepository universityRepository,CompanyRepository companyRepository,UserRepository userRepository){
        this.universityRepository = universityRepository;
        this.companyRepository=companyRepository;
        this.userRepository=userRepository;
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
            CompanyEntity savedEntity = companyRepository.save(companyEntity1);
            university.getCompanyList().add(savedEntity);
            companyDetails.setId(savedEntity.getId());
            return companyDetails;
        }
        return null;
    }

    public List<CompanyDTO> findAllCompaniesVisiting(ObjectId universityId){
        return universityRepository.findById(universityId)
                .map(universityEntity -> universityEntity.getCompanyList()
                        .stream()
                        .map(this::mapToCompanyDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Transactional
    public void addStudentToCompany(List<ObjectId> userIds, ObjectId universityId, ObjectId companyId) {
        universityRepository.findById(universityId).ifPresent(university -> {
            companyRepository.findById(companyId).ifPresent(company -> {
                List<UserEntity> selectedStudents = company.getSelectedStudents();
                List<UserEntity> usersToAdd = userRepository.findAllById(userIds);

                // Add only the users not already in the selectedStudents list
                Set<ObjectId> selectedStudentIds = selectedStudents.stream()
                        .map(UserEntity::getId)
                        .collect(Collectors.toSet());

                usersToAdd.stream()
                        .filter(user -> !selectedStudentIds.contains(user.getId()))
                        .forEach(selectedStudents::add);

                companyRepository.save(company); // Save updated company with new students
            });
        });
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

    private CompanyDTO mapToCompanyDTO(CompanyEntity companyDetails){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(companyDetails.getId());
        companyDTO.setCompanyName(companyDetails.getCompanyName());
        companyDTO.setUniversityId(companyDetails.getUniversityId());
        return companyDTO;
    }


}
