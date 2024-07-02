package com.example.simpleboard2.reply.controller;


import com.example.simpleboard2.reply.db.ReplyEntity;
import com.example.simpleboard2.reply.model.ReplyRequest;
import com.example.simpleboard2.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("")
    public ReplyEntity create(
            @Valid
            @RequestBody ReplyRequest replyRequest
    ){
        return replyService.create(replyRequest);
    }
}
