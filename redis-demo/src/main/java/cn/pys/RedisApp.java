package cn.pys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.pys.mapper")
//@ComponentScan(basePackages = {"cn.pys"})
public class RedisApp {

	public static void main(String[] args) {
		SpringApplication.run(RedisApp.class, args);
	}

}
