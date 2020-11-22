package cn.pys.controller;

import cn.pys.model.User;
import cn.pys.service.UserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("get")
    public String getById(@Param("id") Integer id) {
        User user = userService.getById(id);
        //log.info(JSONObject.toJSONString(user));
        return JSONObject.toJSONString(user);
    }

    @GetMapping("update")
    public String updateById(@Param("id") Integer id) {
        User user = userService.getById(id);
        userService.updateById(user);
        return JSONObject.toJSONString(user);
    }

    @GetMapping("delete")
    public String deleteById(@Param("id") Integer id) {
        userService.deleteById(id);
        return "OK";
    }

    static int count = 0;

    @GetMapping("count")
    public String count() {
        // 添加分布式锁
        RLock lock = redissonClient.getLock("count");
        lock.lock();
        try {
            UserController.count++;
            System.out.println(Thread.currentThread().getName() + "------>" + UserController.count);
        } finally {
            lock.unlock();
        }
        return "OK";
    }


}
