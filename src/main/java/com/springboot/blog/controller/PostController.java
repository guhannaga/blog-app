package com.springboot.blog.controller;

import com.springboot.blog.constants.Constants;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_PATH)
public class PostController {

    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = Constants.PAGE_NO, defaultValue = Constants.ZERO,required = false) Integer pageNo,
            @RequestParam(value = Constants.PAGE_SIZE,defaultValue = Constants.TEN,required = false)Integer pageSize
    ) {
        return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize), HttpStatus.OK);
    }

    @GetMapping(Constants.URL_RESOURCE_IDENTIFIER)
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping(Constants.URL_RESOURCE_IDENTIFIER)
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }


    @DeleteMapping(Constants.URL_RESOURCE_IDENTIFIER)
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(Constants.DELETE_MESSAGE,HttpStatus.OK);
    }
}
