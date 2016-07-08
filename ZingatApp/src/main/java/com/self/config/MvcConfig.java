package com.self.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by akash.p on 2/6/16.
 */
//Not using now. coded in main application
//@Configuration
//@EnableJpaRepositories("com.self.dao")
//@PropertySource("classpath:application.properties")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.datasource.driverClassName}")
    private String driverName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverName);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException, PropertyVetoException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.self");
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        emf.setJpaProperties(jpaProterties());
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws PropertyVetoException, ClassNotFoundException {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }
    @Bean
    public Properties jpaProterties(){
        Properties properties = new Properties();
        properties.put("spring.jpa.show-sql",true);
        properties.put("spring.jpa.hibernate.naming-strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        properties.put("spring.jpa.properties.hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }
}


