package com.example.simple2_board.post.service;


import com.example.simple2_board.board.db.BoardRepository;
import com.example.simple2_board.common.Api;
import com.example.simple2_board.common.Pagination;
import com.example.simple2_board.post.db.PostEntity;
import com.example.simple2_board.post.db.PostRepository;
import com.example.simple2_board.post.model.PostDto;
import com.example.simple2_board.post.model.PostRequest;
import com.example.simple2_board.post.model.PostViewRequest;
import com.example.simple2_board.reply.db.ReplyRepository;
import com.example.simple2_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    public PostDto create(
            PostRequest postRequest

    ){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get();

        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        var saveEntity= postRepository.save(entity);

        return postConverter.toDto(saveEntity);
    }

    public PostEntity view(PostViewRequest postViewRequest) {

       return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map(it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다. %s vs %s";

                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword()));
                    }


                    return it;
                }).orElseThrow(()->{
                  return new RuntimeException("해당 게시글이 존재 하지 않습니다." + postViewRequest.getPostId());
                });


    }

    public  Api<List<PostEntity>> all(Pageable pageable) {
       var list = postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var response = Api.<List<PostEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();

        return response;
    }

    public void delete(PostViewRequest postViewRequest) {


         postRepository.findById(postViewRequest.getPostId())
                .map(it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드가 맞지 않습니다. %s vs %s";

                        throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword()));
                    }

                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);


                    return it;
                }).orElseThrow(()->{
                    return new RuntimeException("해당 게시글이 존재 하지 않습니다." + postViewRequest.getPostId());
                });
    }
}
