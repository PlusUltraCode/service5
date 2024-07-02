package com.example.simpleboard2.reply.model;


import com.example.simpleboard2.reply.db.ReplyEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyRequest {

    @NotNull
    private Long postId;
    @NotBlank
    private String userName;

    @NotBlank
    @Size(min=4 , max=4)
    private String password;

    @NotBlank
    private String title;

    @NotBlank
    private String content;


}
