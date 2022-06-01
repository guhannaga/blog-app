package com.springboot.blog.service;

import com.springboot.blog.constants.Constants;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        Post post = mapToPost(postDto);
        Post newPost = postRepository.save(post);
        return mapToPostDto(newPost);
    }


    private PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .comments(post.getComments()==null?new HashSet<>():post.getComments().stream().map(comment -> mapToCommentDto(comment)).collect(Collectors.toSet()))
                .build();
    }

    private Post mapToPost(PostDto postDto) {

        return Post.builder()
                .id(postDto.getPostId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .comments(postDto.getComments()==null?new HashSet<>():postDto.getComments().stream().map(comment -> mapToComment(comment)).collect(Collectors.toSet()))
                .build();
    }
    private Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getCommentId())
                .commentTitle(commentDto.getCommentTitle())
                .commentBody(commentDto.getCommentBody())
                .commentAuthor(commentDto.getCommentAuthor())
                .build();
    }
    private CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .commentTitle(comment.getCommentTitle())
                .commentBody(comment.getCommentBody())
                .commentAuthor(comment.getCommentAuthor())
                .build();
    }
    @Override
    public List<PostDto> getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> postList = postPage.getContent();
        return postList.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = getPostFromRepository(id);
        return mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = getPostFromRepository(id);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToPostDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = getPostFromRepository(postId);
        postRepository.delete(post);
    }

    private Post getPostFromRepository(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(Constants.POST, Constants.POST_ID, postId));
    }

}
