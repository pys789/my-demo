package cn.pys.controller;

import cn.pys.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/starter")
@Slf4j
public class StarterTestController {
    // 自动装配了my-spring-boot-starter中的服务
    @Autowired
    private HelloService helloService;

    @GetMapping("/info")
    public String info() {
        return helloService.helloWorld();
    }
}
