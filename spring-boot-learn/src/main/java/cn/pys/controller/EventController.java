package cn.pys.controller;

import cn.pys.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class EventController {

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping("/event")
    public String event() {
        MessageEvent event = new MessageEvent(this);
        event.setEvent(1);
        event.setPostId(100L);
        event.setFromUserId(101L);
        event.setToUserId(102L);

        applicationContext.publishEvent(event);
        log.info("------publish finish------");
        return "OK";
    }


}
