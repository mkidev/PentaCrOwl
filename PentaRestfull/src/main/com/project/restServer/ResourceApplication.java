package com.project.restServer;

import com.project.model.Game;
import com.project.model.User;
import com.project.restServer.repositories.GameRepository;
import com.project.restServer.repositories.StreamRepository;
import com.project.restServer.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * Created by Marcel Kisilowski on 07.09.15.
 */
@SpringBootApplication
@EntityScan(basePackages = "com.project.model")
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(final GameRepository gameRepository, UserRepository userRepository, StreamRepository streamRepository) {
        return (args) -> {
            gameRepository.save(new Game("League of legends",99));
            userRepository.save(new User("user","email@mail.de","password"));
        };
    }

//    @Bean
//    public HeaderHttpSessionStrategy sessionStrategy(){
//        return new HeaderHttpSessionStrategy();
//    }
}
