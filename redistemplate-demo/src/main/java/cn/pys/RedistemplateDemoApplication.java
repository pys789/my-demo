package cn.pys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedistemplateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistemplateDemoApplication.class, args);
    }

}
