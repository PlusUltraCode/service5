package com.example.simpleboard2.post.controller;


import com.example.simpleboard2.post.db.db.PostEntity;
import com.example.simpleboard2.post.model.PostRequest;
import com.example.simpleboard2.post.model.PostViewRequest;
import com.example.simpleboard2.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("")
    public void postCreate(
            @Valid
            @RequestBody
            PostRequest postRequest
    ) {
        postService.create(postRequest);
    }

    @PostMapping("view")
    public PostEntity postView(
            @Valid
            @RequestBody
            PostViewRequest postViewRequest
    ){

       return postService.view(postViewRequest);

    }


    @GetMapping("/all")
    public List<PostEntity> list() {
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
