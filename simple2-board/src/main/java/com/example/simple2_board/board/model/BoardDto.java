package com.example.simple2_board.board.model;

import com.example.simple2_board.post.db.PostEntity;
import com.example.simple2_board.post.model.PostDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDto {

    private Long id;

    private String boardName;

    private String status;

    private List<PostDto> postList = List.of();
}
