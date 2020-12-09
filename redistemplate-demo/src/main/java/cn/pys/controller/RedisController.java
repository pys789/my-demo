package cn.pys.controller;

import cn.pys.entity.User;
import cn.pys.service.UserService;
import cn.pys.utils.RedisTemplateUtil;
import com.alibaba.fastjson.JSONObject;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

    @Autowired
    private RedissonClient redissonClient;

    private static int count = 0;

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


    /**
     * 使用redisson实现分布式锁
     */
    @GetMapping("/count")
    public String count() {
        RLock lock = redissonClient.getLock("count");
        lock.lock();
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + "------>" + count);
        } finally {
            lock.unlock();
        }
        return "OK";
    }


}
