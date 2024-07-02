package com.example.simple2_board.post.db;

import com.example.simple2_board.board.db.BoardEntity;
import com.example.simple2_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String title;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(
            mappedBy = "post"
    )
    private List<ReplyEntity> replyList = List.of();
}
