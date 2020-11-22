package cn.pys.config;

import org.springframework.context.annotation.Bean;

public class AutoConfig2 {
    @Bean
    public MyAutoBean2 getMyAutoBean2() {
        System.out.println("------------加载中2,我是spring.factories文件中定义的自动装配--------");
        return new MyAutoBean2();
    }

}
