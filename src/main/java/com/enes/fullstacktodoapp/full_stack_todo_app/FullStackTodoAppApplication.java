package com.enes.fullstacktodoapp.full_stack_todo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FullStackTodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullStackTodoAppApplication.class, args);
    }


    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
