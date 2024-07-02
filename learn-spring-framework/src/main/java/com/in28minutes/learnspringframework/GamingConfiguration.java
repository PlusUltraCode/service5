package com.in28minutes.learnspringframework;


import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GameingConsole;
import com.in28minutes.learnspringframework.game.MarioGame;
import com.in28minutes.learnspringframework.game.PackmanGame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GamingConfiguration {

    @Bean
    public GameingConsole game(){
        var game = new PackmanGame();
        return game;
    }

    @Bean

    public GameRunner gameRunner(GameingConsole game){
        var gameRunner = new GameRunner(game);
        return gameRunner;
    }

    @Bean
    @Primary
    public MarioGame marioRunner(){
        return new MarioGame();
    }

}
