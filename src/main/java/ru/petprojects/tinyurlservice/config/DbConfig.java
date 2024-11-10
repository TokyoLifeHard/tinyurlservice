package ru.petprojects.tinyurlservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    @Scope("prototype")
    public NamedParameterJdbcTemplate template(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
