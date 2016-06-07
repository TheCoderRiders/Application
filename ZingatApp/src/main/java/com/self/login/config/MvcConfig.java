package com.self.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by akash.p on 2/6/16.
 */

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        //registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("index");
        registry.addViewController("/login").setViewName("index");
        registry.addViewController("/403").setViewName("accessDenied");
    }*/
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/userbase");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("P@ssw0rd@123");
        return driverManagerDataSource;
    }

    /*@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/static/");
        resolver.setSuffix(".html");
        return resolver;
    }*/
}


