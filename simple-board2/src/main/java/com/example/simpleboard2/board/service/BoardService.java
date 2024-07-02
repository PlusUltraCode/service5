package com.example.simpleboard2.board.service;


import com.example.simpleboard2.board.db.BoardEntity;
import com.example.simpleboard2.board.db.BoardRepository;
import com.example.simpleboard2.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public void create(BoardRequest boardRequest) {

        var boardEntity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        boardRepository.save(boardEntity);
    }

}
