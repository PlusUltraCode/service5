package com.in28minutes.learnspringframework.game;


import org.springframework.stereotype.Component;

@Component
public class PackmanGame implements GameingConsole{


    @Override
    public void up() {
        System.out.println("P up");
    }

    @Override
    public void down() {
        System.out.println("P donw");
    }

    @Override
    public void left() {
        System.out.println("P left");
    }

    @Override
    public void right() {
        System.out.println("P right");
    }
}
