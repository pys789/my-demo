package cn.pys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfig2 {
    @Bean
    public MyAutoBean2 myAutoBean2() {
        System.out.println("------------加载中2,我是 @AutoSelectorConfigAnno(isLinux = false) 时的自动装配--------");
        return new MyAutoBean2();
    }

}
