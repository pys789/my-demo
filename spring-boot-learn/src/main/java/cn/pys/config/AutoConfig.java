package cn.pys.config;

import org.springframework.context.annotation.Bean;

public class AutoConfig {
    @Bean
    public MyAutoBean getMyAutoBean() {
        System.out.println("--------加载中，我是AutoConfig-----------------------");
        return new MyAutoBean();
    }

}
