package cn.pys.controller;

import cn.pys.config.MyAutoBean;
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

    @RequestMapping("/autoConfig")
    public String autoConfig() {
        return myAutoBean.hello("pys");
    }


}
