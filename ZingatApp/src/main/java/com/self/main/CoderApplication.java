package com.self.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by akash.p on 2/6/16.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.self.login.*")
public class CoderApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CoderApplication.class, args);
    }
}
