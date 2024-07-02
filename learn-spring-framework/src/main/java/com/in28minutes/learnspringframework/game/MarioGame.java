package com.in28minutes.learnspringframework.game;


import org.springframework.stereotype.Component;

@Component
public class MarioGame implements GameingConsole{

    public void up() {
        System.out.println("Jump");
    }

    public void down() {
        System.out.println("down");
    }

    public void left() {
        System.out.println("Go back");
    }

    public void right() {
        System.out.println("Accelerate");
    }
}
