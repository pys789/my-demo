package cn.pys;

import cn.pys.annotation.AutoConfigAnno;
import cn.pys.annotation.AutoSelectorConfigAnno;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@AutoConfigAnno
@AutoSelectorConfigAnno(isLinux = true)
public class App {

    /**
     * @EnableWebMvc, @EnableCaching,@ConditionXXX 都属于手动装配,，
     * 在 spring.factories 定义的配置不需要加注解，直接注入，这种属于自动装配。
     * spring boot中就通过这种方式添加了很多自动配置类
     */

    /**
     * 自定义注解 @AutoConfigAnno 类似于 @EnableWebMvc,通过注解引入 jar 包中的 webmvc 包，
     * 如果直接使用 @Configuration,是直接扫描当前包或子包配置
     */

    /**
     * 自定义注解 @AutoSelectorConfigAnno(isLinux = true) 类似于 @EnableCaching,
     * 不同的是这个注解可以根据不同的注解参数装配不同的配置
     */

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
