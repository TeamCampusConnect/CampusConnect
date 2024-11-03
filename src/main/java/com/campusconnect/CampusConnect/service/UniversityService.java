package com.campusconnect.CampusConnect.service;
import com.campusconnect.CampusConnect.DtoConverstion.DtoConverterHelper;
import com.campusconnect.CampusConnect.dto.CompanyDTO;
import com.campusconnect.CampusConnect.dto.UniversityNameListDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.entity.CompanyEntity;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.exception.EntityNotFoundException;
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

    private final DtoConverterHelper dtoConverterHelper;

    UniversityService(UniversityRepository universityRepository,CompanyRepository companyRepository,UserRepository userRepository,DtoConverterHelper dtoConverterHelper ){
        this.universityRepository = universityRepository;
        this.companyRepository=companyRepository;
        this.userRepository=userRepository;
        this.dtoConverterHelper=dtoConverterHelper;
    }

    public List<UniversityNameListDTO> getAllUniversities() {
        return universityRepository.findAllNamesOfUniversity();
    }

    public List<UserDTO> findAllStudents(ObjectId universityId) {
        return universityRepository.findById(universityId)
                .map(university -> university.getAllStudents().stream()
                        .map(dtoConverterHelper::entityToUserDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Transactional
    public CompanyDTO createCompany(ObjectId universityId, CompanyDTO companyDetails) {

        UniversityEntity university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + universityId));

        CompanyEntity companyEntity = dtoConverterHelper.mapToCompanyEntity(companyDetails);

        CompanyEntity savedEntity = companyRepository.save(companyEntity);
        System.out.println("Saved company entity with ID: " + savedEntity.getId());

        university.getCompanyList().add(savedEntity);
        universityRepository.save(university);

        companyDetails.setId(savedEntity.getId());
        return companyDetails;
    }




    public List<CompanyDTO> findAllCompaniesVisiting(ObjectId universityId){
        return universityRepository.findById(universityId)
                .map(universityEntity -> universityEntity.getCompanyList()
                        .stream()
                        .map(dtoConverterHelper::mapToCompanyDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Transactional
    public void addStudentToCompany(List<ObjectId> userIds, ObjectId universityId, ObjectId companyId) {
        UniversityEntity university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + universityId));

        CompanyEntity company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));

        List<UserEntity> selectedStudents = company.getSelectedStudents();
        List<UserEntity> usersToAdd = userRepository.findAllById(userIds);

        Set<ObjectId> selectedStudentIds = selectedStudents.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toSet());

        usersToAdd.stream()
                .filter(user -> !selectedStudentIds.contains(user.getId()))
                .forEach(selectedStudents::add);

        companyRepository.save(company);
    }

    public List<UserDTO> getAllUsersSelectedForCompany(ObjectId universityId , ObjectId companyId){
      UniversityEntity university = universityRepository.findById(universityId).orElseThrow(() -> new EntityNotFoundException("University not found."));
     Optional<CompanyEntity> companyEntityOptional = university.getCompanyList().stream()
             .filter(company -> company.getId().equals(companyId))
             .findFirst();
     CompanyEntity companyEntity = companyEntityOptional.orElseThrow(()-> new EntityNotFoundException("Company not found."));
     List<UserEntity> selectedStudents = companyEntity.getSelectedStudents();
     return selectedStudents.stream().map(dtoConverterHelper::entityToUserDTO).collect(Collectors.toList());
     }






}
