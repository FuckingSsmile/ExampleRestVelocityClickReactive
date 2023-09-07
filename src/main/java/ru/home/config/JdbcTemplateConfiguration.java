package ru.home.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Конфигурационный класс для JdbcTemplate клиента
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Setter
public class JdbcTemplateConfiguration {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    /**
     * Создает и настраивает объект DataSource для подключения к БД.
     *
     * @return объект DataSource
     */
    @Bean
    public DataSource clickHouseDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}