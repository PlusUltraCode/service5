package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GameingConsole;
import com.in28minutes.learnspringframework.game.MarioGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.learnspringframework.game")
public class GamingAppLauncherApplication {

    public static void main(String[] args) {

        try(var context =
                    new AnnotationConfigApplicationContext
                (GamingConfiguration.class))
        {
            context.getBean(GameingConsole.class).up();
            context.getBean(GameRunner.class).run();

        }
    }
}
