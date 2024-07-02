package com.example.simple2_board.board.service;

import com.example.simple2_board.board.db.BoardEntity;
import com.example.simple2_board.board.model.BoardDto;
import com.example.simple2_board.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;

    public BoardDto toDto(BoardEntity boardEntity){

       var postList= boardEntity.getPostList().stream()
               .map(postConverter::toDto)
               .toList();


       return  BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }

}
