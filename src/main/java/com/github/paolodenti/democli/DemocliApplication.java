package com.github.paolodenti.democli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemocliApplication {

    public static void main(String[] args) {

        System.exit(SpringApplication.exit(SpringApplication.run(DemocliApplication.class, args)));
    }
}
