package com.campusconnect.CampusConnect.service;
import com.campusconnect.CampusConnect.dto.UniversityNameListDTO;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityService  {

    private final UniversityRepository universityRepository;
    UniversityService(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    public List<UniversityNameListDTO> getAllUniversities() {
        return universityRepository.findAllNamesOfUniversity();
    }

}
