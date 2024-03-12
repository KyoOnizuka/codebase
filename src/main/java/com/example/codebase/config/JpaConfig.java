package com.example.codebase.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class JpaConfig {
    @Bean("entityManager")
    public EntityManagerFactory entityManagerFactory() {
        Map<String, String> properties = new HashMap<>();

        // JDBC connection properties
        properties.put("javax.persistence.jdbc.url", "jdbc:your_database_url");
        properties.put("javax.persistence.jdbc.user", "your_database_username");
        properties.put("javax.persistence.jdbc.password", "your_database_password");
        properties.put("javax.persistence.jdbc.driver", "your_database_driver");
        properties.put("hibernate.dialect", "your_hibernate_dialect");

        return Persistence.createEntityManagerFactory("YourPersistenceUnit", properties);
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("your.database.driver");
        dataSource.setUrl("your.database.url");
        dataSource.setUsername("your.database.username");
        dataSource.setPassword("your.database.password");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySpring() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("your.entity.package");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Additional JPA properties
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "your.hibernate.dialect");
        // Add other properties as needed

        em.setJpaProperties(properties);

        return em;
    }
}
