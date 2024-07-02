package com.in28minutes.springboot2.learnjpaandhibernate.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class coursejdbcRepository {

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    //insert, delete, select

    private static String INSERT_QUERY =
            """
                     insert into course(id, name, author)
                     values(?,?,?)
                    """;

    private static String DELETE_QUERY =
            """
                    delete from course
                    where id = ?
                    """;

    private static String SELECT_QUERY =
            """
                    select * from course
                    where id = ?
                    
                    """;

    public static void  insert(Course course) {
        jdbcTemplate.update(INSERT_QUERY,course.getId(),course.getName()
        ,course.getAuthor());
    }

    public static void  delete(long id){
        jdbcTemplate.update(DELETE_QUERY,id);
    }

    public static Course findById(long id){
        return jdbcTemplate.queryForObject(
                SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class),id
        );
    }

}
