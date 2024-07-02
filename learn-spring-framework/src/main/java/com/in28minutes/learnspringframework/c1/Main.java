package com.in28minutes.learnspringframework.c1;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;



public class Main {
    public static void main(String[] args) {
        try (
                var context =
                        new ClassPathXmlApplicationContext("contextConfiguration.xml");

        ) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
            System.out.println(context.getBean("name"));

            System.out.println(context.getBean("age"));
        }

    }
}
