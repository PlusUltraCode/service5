package com.example.simpleboard2.post.service;


import com.example.simpleboard2.post.db.db.PostEntity;
import com.example.simpleboard2.post.db.db.PostRepository;
import com.example.simpleboard2.post.model.PostRequest;
import com.example.simpleboard2.post.model.PostViewRequest;
import com.example.simpleboard2.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;

    public void create(PostRequest postRequest) {

        var postEntity = PostEntity.builder()
                .boardId(1L)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .content(postRequest.getContent())
                .email(postRequest.getEmail())
                .title(postRequest.getTitle())
                .status("REGISTERED")
                .postedAt(LocalDateTime.now())
                .build();

        postRepository.save(postEntity);
    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }





    public void delete(PostViewRequest postViewRequest) {

        postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it -> {
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        var message = String.format(format,it.getPassword(), postViewRequest.getPassword());
                        throw new RuntimeException(message);
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;
                })
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("해당 게시물은 존재하지 않습니다.");
                        }
                );

    }


    public PostEntity view(PostViewRequest postViewRequest) {

       return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map(it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "비밀번호가 일치하지 않습니다 %s vs %s";
                        var message = String.format(format, it.getPassword(),postViewRequest.getPassword());
                        throw new RuntimeException(message);
                    }

                    var replylist = replyService.findAllByPostId(it.getId());
                    it.setReplyList(replylist);

                    return it;
                })
                .orElseThrow(()->{
                    throw new RuntimeException("해당 게시물은 존재하지 않습니다.");
                });
    }
}
