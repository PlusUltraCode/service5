package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {

    GameingConsole game;

    public GameRunner(@Qualifier("SuperContraGameQualifier") GameingConsole game) {
        this.game = game;
    }

    public void run(){
        System.out.println("Running gmae: "+ game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
