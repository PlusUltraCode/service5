package com.example.simple2_board.reply.service;


import com.example.simple2_board.post.db.PostEntity;
import com.example.simple2_board.post.db.PostRepository;
import com.example.simple2_board.reply.db.ReplyEntity;
import com.example.simple2_board.reply.db.ReplyRepository;
import com.example.simple2_board.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;


    public ReplyEntity create(
            ReplyRequest replyRequest
    ){

        var postEntity = postRepository.findById(replyRequest.getPostId()).get();

        var entity = ReplyEntity.builder()
                .post(postEntity)
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId){

        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }
}
