package cn.pys.controller;

import cn.pys.utils.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RedisController {

    @Autowired
    RedisTemplateUtil redisTemplateUtil;

    @RequestMapping("/hello")
    public String redisTest() {
        redisTemplateUtil.set("name", "pys");
        return redisTemplateUtil.get("name");
    }


}
