package cn.pys.controller;

import cn.pys.utils.RedisTemplateUtil;
import cn.pys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RedisController {

    @Autowired
    RedisTemplateUtil redisTemplateUtil;

    @RequestMapping("/hello")
    public String hello() {
        redisTemplateUtil.set("name", "pys");
        return redisTemplateUtil.get("name");
    }

    @RequestMapping("/saveUser")
    public String saveUser() {
        UserVO userVO = new UserVO(2, "test2");
        redisTemplateUtil.set("user", userVO);
        return redisTemplateUtil.get("user");
    }


}
