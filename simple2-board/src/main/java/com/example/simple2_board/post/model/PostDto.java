package com.example.simple2_board.post.model;


import com.example.simple2_board.board.db.BoardEntity;
import com.example.simple2_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {

    private Long id;

    private Long boardId;

    private String userName;

    private String password;

    private String email;

    private String title;

    private String status;

    private String content;

    private LocalDateTime postedAt;



}
