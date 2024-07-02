package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.PackmanGame;

public class App01GamingBasicJava {
    public static void main(String[] args) {

        //var game = new MarioGame();
      //  var game = new SuperContraGame();
        var game = new PackmanGame();
        var gameRunner = new GameRunner(game);
        gameRunner.run();
    }
}
