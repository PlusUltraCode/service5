package com.in28minutes.springboot.learnspringboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    @RequestMapping("/course")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
                new Course(1, "Learn AWS", "in28minutes"),
                new Course(2, "Learn AWS2", "in28minutes2"),
                new Course(2, "Learn AWS2", "in28minutes2"),
                new Course(74123, "Learn AWS2", "in28minutes2")

        );
    }
}
