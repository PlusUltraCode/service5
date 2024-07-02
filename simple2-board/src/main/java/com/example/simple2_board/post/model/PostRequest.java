package com.example.simple2_board.post.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {

    private Long boardId =1L;

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min=4,max=4)
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String content;


}
