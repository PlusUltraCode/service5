package com.example.simpleboard.post.service;


import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;

    public PostEntity create(
            PostRequest postRequest
    ) {
        var entity = PostEntity.builder()
                .boardId(1L)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }


    //게시글이 있는가
    // 비밃번호가 있는가
    public PostEntity view(PostViewRequest postViewRequest) {

        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it -> {
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        var message = String.format(format, it.getPassword(), postViewRequest.getPassword());
                        throw new RuntimeException(message);
                    }

                    var replylist = replyService.findAllByPostId(it.getId());
                    it.setReplyList(replylist);

                    return it;


                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId());
                        }
                );

    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }

    public void delete(PostViewRequest postViewRequest) {


        postRepository.findById(postViewRequest.getPostId())
                .map(it -> {
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        var message = String.format(format, it.getPassword(), postViewRequest.getPassword());

                        throw new RuntimeException(message);
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId());
                        }
                );

    }
}
