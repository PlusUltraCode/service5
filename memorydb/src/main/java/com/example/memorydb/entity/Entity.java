package com.example.memorydb.entity;


import lombok.Data;

@Data
public abstract class Entity implements PrimaryKey{

    private Long id;

}
