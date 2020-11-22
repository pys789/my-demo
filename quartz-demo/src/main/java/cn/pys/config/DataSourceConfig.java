package cn.pys.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description
 * @Date 2020/11/16 10:15
 * @Created by pengys
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.quartz.properties.org.quartz.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.quartz.properties.org.quartz.datasource.url}")
    private String url;

    @Value("${spring.quartz.properties.org.quartz.datasource.username}")
    private String username;

    @Value("${spring.quartz.properties.org.quartz.datasource.password}")
    private String password;

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
