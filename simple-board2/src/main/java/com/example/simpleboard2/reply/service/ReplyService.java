package com.example.simpleboard2.reply.service;


import com.example.simpleboard2.reply.db.ReplyEntity;
import com.example.simpleboard2.reply.db.ReplyRepository;
import com.example.simpleboard2.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyEntity create(ReplyRequest replyRequest) {

        var replyEntity = ReplyEntity.builder()
                .userName(replyRequest.getUserName())
                .postId(replyRequest.getPostId())
                .status("REGISTERED")
                .password(replyRequest.getPassword())
                .content(replyRequest.getContent())
                .title(replyRequest.getTitle())
                .repliedAt(LocalDateTime.now())
                .build();

        replyRepository.save(replyEntity);

        return replyEntity;
    }

    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }
}
