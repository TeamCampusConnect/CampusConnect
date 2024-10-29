package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.dto.PostDTO;
import com.campusconnect.CampusConnect.entity.PostEntity;
import com.campusconnect.CampusConnect.entity.UniversityEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.PostRepository;
import com.campusconnect.CampusConnect.repositories.UniversityRepository;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final UniversityRepository universityRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository , UniversityRepository universityRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.universityRepository=universityRepository;
    }

    // Creating post
    @Transactional
    public void createPost(ObjectId userId, PostDTO postData) {
        try {
            System.out.println("The user id Is : " + userId);
            Optional<UserEntity> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                UserEntity user = userOpt.get();
                System.out.println("user found" + user.toString());
                // Convert DTO to Entity
                PostEntity post = DtoToObjMapping(postData);
                System.out.println("post created"+post);

                // Set references
                post.setUsersId(userId);
                post.setUniversityId(user.getUniversityId());

                System.out.println("Post universityId is saved and userId is saved to.");
                System.out.println(post.toString());

                Optional<UniversityEntity> universityEntityOptional = universityRepository.findById(user.getUniversityId());

//                Saving the post.
                postRepository.save(post);

                 if(universityEntityOptional.isPresent()){
                     System.out.println("University found");
                     UniversityEntity university = universityEntityOptional.get();
                     System.out.println(university.toString());
                     university.getUniversityRelatedPosts().add(post);
                     universityRepository.save(university);
                     System.out.println("Updated university");
                     System.out.println(university.toString());
                 }
                 else {
                     System.out.println("University not found");
                     throw new RuntimeException("University not found.");
                 }

                System.out.println("same error occured when saving the posts ");


                System.out.println(post.toString());

                user.getPosts().add(post);
                userRepository.save(user);
            } else {
                System.out.println("user not found");
                throw new RuntimeException("User not found.");
            }
        } catch (Exception e) {
            System.out.println("Some error occured"+e.getMessage());
            throw new RuntimeException("An error occurred while saving the post.", e);
        }
    }

    // Getting a post by post ID
    public PostEntity getPostById(ObjectId postId) {
        Optional<PostEntity> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            return postOpt.get();
        } else {
            throw new RuntimeException("Post not found.");
        }
    }

    // Updating a post by post ID
    @Transactional
    public void updatePost(ObjectId postId, PostDTO updatedPostData) {
        Optional<PostEntity> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            PostEntity post = postOpt.get();

            // Update fields
            post.setTitle(updatedPostData.getTitle());
            post.setContent(updatedPostData.getContent());
            post.setImageUri(updatedPostData.getImageUri());

            // Save updated post
            postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found.");
        }
    }

    // Deleting a post by post ID
    @Transactional
    public void deletePost(ObjectId postId) {
        try {
            Optional<PostEntity> postOpt = postRepository.findById(postId);
            if (postOpt.isPresent()) {
                PostEntity post = postOpt.get();

                // Delete post from the repository
                postRepository.delete(post);

                // Optionally, you could remove the post reference from the user as well
                Optional<UserEntity> userOpt = userRepository.findById(post.getUsersId());
                if (userOpt.isPresent()) {
                    UserEntity user = userOpt.get();
                    user.getPosts().remove(post);
                    userRepository.save(user);
                }
            } else {
                throw new RuntimeException("Post not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the post.", e);
        }
    }

//    Get all posts for a university
//    page: The current page number (1-based index).
//    pageSize: The number of posts per page.
public List<PostDTO> getAllPostsForUniversity(ObjectId universityId, int page, int pageSize) {
    return universityRepository.findById(universityId)
            .map(university -> university.getUniversityRelatedPosts().stream()
                    .skip((long) (page - 1) * pageSize)  // Skip posts for previous pages
                    .limit(pageSize)                    // Limit the results to the page size
                    .map(this::PostToDto)
                    .toList())
            .orElse(Collections.emptyList());
}


    // Helper method to map DTO to Entity
    private PostEntity DtoToObjMapping(PostDTO postDTO) {
        PostEntity post = new PostEntity();
        post.setUserName(postDTO.getUserName());
        post.setUsersId(postDTO.getUserId());
         post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUri(postDTO.getImageUri());
        post.setCreatedAt(new Date());
        return post;
    }

    private PostDTO PostToDto(PostEntity post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setUserId(post.getUsersId());
        dto.setImageUri(post.getImageUri());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setUserName(post.getUserName());
        dto.setCreatedAt(post.getCreatedAt());
        return dto;
    }




}
