package com.in28minutes.springboot2.learnjpaandhibernate.study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CoursejdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CoursejdbcCommandLineRunner coursejdbcCommandLineRunner;

    @Override
    public void run(String... args) throws Exception {
        coursejdbcRepository.insert(new Course(1,"dongho","WKd"));
        coursejdbcRepository.insert(new Course(2,"dongho","WKd"));
        coursejdbcRepository.insert(new Course(3,"dongho","WKd"));
        coursejdbcRepository.insert(new Course(4,"dongho","WKd"));

        System.out.println(coursejdbcRepository.findById(1));
        System.out.println(coursejdbcRepository.findById(2));

        coursejdbcRepository.delete(1);
        coursejdbcRepository.delete(2);


    }
}
