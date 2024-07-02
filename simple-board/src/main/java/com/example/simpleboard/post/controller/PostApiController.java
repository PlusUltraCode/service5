package com.example.simpleboard.post.controller;


import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final PostRepository postRepository;


    @PostMapping("")
    public PostEntity create(
            @Valid
            @RequestBody
            PostRequest postRequest
    ) {
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostEntity view(
            @RequestBody PostViewRequest postViewRequest
    ) {
        return postService.view(postViewRequest);

    }

    @GetMapping("/all")
    public List<PostEntity> list(

    ) {
        return postService.all();
    }


    @PostMapping("/delete")
    public void delete(
        @Valid
        @RequestBody PostViewRequest postViewRequest
    ) {

        postService.delete(postViewRequest);
    }

}
