package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToComment(commentDto);
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToCommentDto(newComment);
    }

    private Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .commentId(commentDto.getCommentId())
                .commentTitle(commentDto.getCommentTitle())
                .commentBody(commentDto.getCommentBody())
                .commentAuthor(commentDto.getCommentAuthor())
                .build();
    }

    private CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .commentTitle(comment.getCommentTitle())
                .commentBody(comment.getCommentBody())
                .commentAuthor(comment.getCommentAuthor())
                .build();
    }
}
