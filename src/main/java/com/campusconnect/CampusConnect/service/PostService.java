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

import java.util.Date;
import java.util.Optional;

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
            Optional<UserEntity> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                UserEntity user = userOpt.get();

                // Convert DTO to Entity
                PostEntity post = DtoToObjMapping(postData);

                // Set references
                post.setUsersId(userId);
                post.setUniversityId(user.getUniversityId());

                 Optional<UniversityEntity> universityEntityOptional = universityRepository.findById(user.getUniversityId());

                 if(universityEntityOptional.isPresent()){
                     UniversityEntity university = universityEntityOptional.get();
                     university.getUniversityRelatedPosts().add(post);
                     universityRepository.save(university);
                 }
                 else {
                     throw new RuntimeException("University not found.");
                 }

                // Save post
                postRepository.save(post);

                // Add post to user's posts and update user
                user.getPosts().add(post);
                userRepository.save(user);
            } else {
                throw new RuntimeException("User not found.");
            }
        } catch (Exception e) {
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

    // Helper method to map DTO to Entity
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
}
