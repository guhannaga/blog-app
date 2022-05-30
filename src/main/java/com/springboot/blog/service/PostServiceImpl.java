package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /*
     *convert from PostDto to Entity
     * save it
     * convert back to PostDto from Entity
     */

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .build();
        Post newPost = postRepository.save(post);
        PostDto postResponse = PostDto.builder()
                .id(newPost.getId())
                .title(newPost.getTitle())
                .description(newPost.getDescription())
                .content(newPost.getContent())
                .build();

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }
}
