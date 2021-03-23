package cn.pys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfig3 {
    @Bean
    public MyAutoBean3 myAutoBean3() {
        System.out.println("------------加载中3,我是spring.factories文件中定义的自动装配--------");
        return new MyAutoBean3();
    }

}
