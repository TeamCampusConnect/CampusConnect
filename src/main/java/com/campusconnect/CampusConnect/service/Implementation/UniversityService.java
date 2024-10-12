package com.campusconnect.CampusConnect.service.Implementation;
import com.campusconnect.CampusConnect.dto.UniversityNameListDTO;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import com.campusconnect.CampusConnect.service.UniversityServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityService implements UniversityServiceInterface {

    private final UniversityRepository universityRepository;
    UniversityService(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    @Override
    public List<UniversityNameListDTO> getAllUniversities() {
        return universityRepository.findAllNamesOfUniversity();
    }

}
