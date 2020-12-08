package cn.pys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.pys.mapper")
@EnableCaching
public class RedisApp {

	public static void main(String[] args) {
		SpringApplication.run(RedisApp.class, args);
	}

}
