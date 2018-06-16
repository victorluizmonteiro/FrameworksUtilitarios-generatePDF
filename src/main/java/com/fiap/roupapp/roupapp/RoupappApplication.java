package com.fiap.roupapp.roupapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class RoupappApplication {



    public static void main(String[] args) {
        // Launch the application


        SpringApplication.run(RoupappApplication.class, args);

    }






}
