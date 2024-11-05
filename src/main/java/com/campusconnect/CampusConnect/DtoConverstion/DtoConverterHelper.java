package com.campusconnect.CampusConnect.DtoConverstion;

import com.campusconnect.CampusConnect.dto.*;
import com.campusconnect.CampusConnect.entity.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class DtoConverterHelper {

//    UserDto to user entity  converter
    public UserEntity userDtoToEntity(UserDTO dto) {
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

//  user entity to dto converter
public UserDTO entityToUserDTO(UserEntity userEntity) {
    UserDTO userDTO = new UserDTO();
    userDTO.setUniversityId(userEntity.getUniversityId());
    userDTO.setId(userEntity.getId());
    userDTO.setEmail(userEntity.getEmail());
    userDTO.setUserName(userEntity.getUserName());
    userDTO.setBranch(userEntity.getBranch());
    userDTO.setUniversityReg(userDTO.getUniversityReg());
    userDTO.setCurrentCompany(userEntity.getCurrentCompany());
    userDTO.setNameOfUniversity(userEntity.getNameOfUniversity());
    return userDTO;
}


    //    UniversityDto to user entity  converter
    public UniversityEntity universityDtoToEntity(UniversityDTO dto) {
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

//     post entity to dto converter
    public PostDTO PostObjToDTOMapping(PostEntity postData) {
        return new PostDTO(
                postData.getId(),
                postData.getUsersId(),
                postData.getUserName(),
                postData.getTitle(),
                postData.getContent(),
                postData.getImageUri(),
                postData.getCreatedAt()
        );
    }

    //      post dto to post entity converter
    public PostEntity DtoToObjMapping(PostDTO postDTO) {
        PostEntity post = new PostEntity();
        post.setUserName(postDTO.getUserName());
        post.setUsersId(postDTO.getUserId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUri(postDTO.getImageUri());
        post.setCreatedAt(new Date());
        return post;
    }


    public CompanyEntity mapToCompanyEntity(CompanyDTO companyDetails){
        CompanyEntity companyEntity1 = new CompanyEntity();
        companyEntity1.setCompanyName(companyDetails.getCompanyName());
        return companyEntity1;
    }

    public CompanyDTO mapToCompanyDTO(CompanyEntity companyDetails){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(companyDetails.getId());
        companyDTO.setCompanyName(companyDetails.getCompanyName());
        return companyDTO;
    }

    public ChatDTO chatEntityToDto(ChatEntity chatEntity){
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chatEntity.getId());
        chatDTO.setSender(chatEntity.getSender());
        chatDTO.setReceiver(chatDTO.getReceiver());
        return chatDTO;
    }
    public ChatEntity chatDtoToEntity(ChatDTO chatDTO){
        return new ChatEntity();
    }


}
