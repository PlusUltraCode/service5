package com.example.simple2_board.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {

    private Integer page;

    private Integer size;

    private Integer currentElements;

    private Integer totalPage;

    private Long totalElements;
}
