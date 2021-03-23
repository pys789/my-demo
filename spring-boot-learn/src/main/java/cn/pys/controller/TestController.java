package cn.pys.controller;

import cn.pys.config.AutoConfig;
import cn.pys.config.MyAutoBean;
import cn.pys.config.MyAutoBean3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    // 开启 @AutoConfigAnno 注解后，注解中引入的config中的MyAutoBean可以直接引用
    @Autowired
    private MyAutoBean myAutoBean;

    @Autowired
    private MyAutoBean3 myAutoBean3;

    @Autowired
    private AutoConfig autoConfig;

    @RequestMapping("/autoConfig")
    public String autoConfig() {
        System.out.println(autoConfig.myAutoBean().hello("AutoConfig类上面没有加任何注解,因为有 @Import({AutoConfig.class}),所有自动装配好了"));
        System.out.println(myAutoBean3.hello("自动装配的配置,不需要手动开启"));
        return myAutoBean.hello("pys");
    }


}
