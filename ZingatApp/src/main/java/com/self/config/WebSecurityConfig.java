package com.self.config;

import com.self.constants.LoginConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by akash.p on 2/6/16.
 */

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new MySqlPasswordEncoder())
                .usersByUsernameQuery(LoginConstants.SELECT_USER_AUTHENTICATION)
                .authoritiesByUsernameQuery(LoginConstants.SELECT_USER_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/app/**","/node_modules/**").permitAll()
                .anyRequest().authenticated()
                ;
    }
}
