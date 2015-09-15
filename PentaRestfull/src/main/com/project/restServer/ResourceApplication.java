package com.project.restServer;

import com.project.model.Game;
import com.project.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

/**
 * Created by Marcel Kisilowski on 07.09.15.
 */
@SpringBootApplication
@EntityScan(basePackages = "com.project.model")
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

//    @Bean
//    public HeaderHttpSessionStrategy sessionStrategy(){
//        return new HeaderHttpSessionStrategy();
//    }
}
