package cn.pys.controller;

import cn.pys.entity.User;
import cn.pys.service.UserService;
import cn.pys.utils.RedisTemplateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public User saveUser(@PathVariable(name = "userId") Integer userId) {
        return userService.saveUser(userId, "test" + userId);
    }

    @GetMapping("/delete/{userId}")
    public int delete(@PathVariable(name = "userId") Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/update/{userId}")
    public Integer updateUser(@PathVariable(name = "userId") Integer userId, @RequestParam(value = "name") String name) {
        return userService.updateByUserId(userId, name);
    }

    @GetMapping("/get/{userId}")
    public User getUser(@PathVariable(name = "userId") Integer userId) {
        User user = userService.getUserById(userId);
        return user;
    }

    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.getAll();
    }


}
