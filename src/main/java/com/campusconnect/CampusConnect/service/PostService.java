package com.campusconnect.CampusConnect.service;

import com.campusconnect.CampusConnect.dto.PostDTO;
import com.campusconnect.CampusConnect.entity.PostEntity;
import com.campusconnect.CampusConnect.entity.UserEntity;
import com.campusconnect.CampusConnect.repositories.PostRepository;
import com.campusconnect.CampusConnect.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Creating post
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

                // Save post
                postRepository.save(post);

                // Add post to user's posts and update user
                user.getPosts().add(post);  // Using user.getPosts()
                userRepository.save(user);  // Save the user entity after adding the post
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
                    user.getPosts().remove(post);  // Remove post reference
                    userRepository.save(user);     // Save the updated user
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
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUri(postDTO.getImageUri());
        post.setCreatedAt(new Date());
        return post;
    }
}
