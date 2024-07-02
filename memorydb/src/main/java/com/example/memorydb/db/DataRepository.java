package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T,ID> extends Repository<T,ID> {

    //create , update
    T save(T date);

    //read
    Optional<T> findById(ID id);


    List<T> findAll();

    //delete
    void delete(ID id);
}
