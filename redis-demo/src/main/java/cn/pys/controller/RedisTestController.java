package cn.pys.controller;

import cn.pys.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2020/12/4 16:23
 * @Created by pengys
 */
@RestController
@Slf4j
@RequestMapping("/redis")
public class RedisTestController {

    static int count = 0;

    @Autowired
    JedisUtil redisUtil;

    @GetMapping("/lock")
    public Integer lock() throws InterruptedException {
        Long lock = redisUtil.setnx("lock", "1");
        if (lock != null && lock.intValue() == 1) {
            Thread.sleep(100);
            redisUtil.del("lock");
            count++;
            return count;
        } else {
            return -1;
        }
    }
}
