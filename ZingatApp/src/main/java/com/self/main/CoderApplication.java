package com.self.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by akash.p on 2/6/16.
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(value = "com.self.dao")
@EntityScan("com.self.models")
@ComponentScan("com.self")
@EnableTransactionManagement
public class CoderApplication {
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CoderApplication.class, args);
    }
}
