package com.example.simpleboard2.board.controller;


import com.example.simpleboard2.board.model.BoardRequest;
import com.example.simpleboard2.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;


    @PostMapping("")
    public void boardInit(
            @Valid
            @RequestBody BoardRequest boardRequest
    ){
        boardService.create(boardRequest);
    }
}
