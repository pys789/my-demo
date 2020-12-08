package cn.pys.controller;

import cn.pys.entity.User;
import cn.pys.service.UserService;
import cn.pys.utils.RedisTemplateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class RedisController {

    @Resource
    RedisTemplateUtil redisTemplateUtil;

    @Resource
    UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        redisTemplateUtil.set("name", "pys");
        return redisTemplateUtil.get("name");
    }

    @GetMapping("/save/{userId}")
    public String saveUser(@PathVariable(name = "userId") Integer userId) {
        User user = userService.saveUser(userId, "test" + userId);
        return user == null ? "" : JSONObject.toJSONString(user);
    }


}
