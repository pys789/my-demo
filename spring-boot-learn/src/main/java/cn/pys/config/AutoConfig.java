package cn.pys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfig {
    @Bean
    public MyAutoBean myAutoBean() {
        System.out.println("--------加载中，我是AutoConfig, 也是 @AutoSelectorConfigAnno(isLinux = true)时的自动装配");
        return new MyAutoBean();
    }

}
