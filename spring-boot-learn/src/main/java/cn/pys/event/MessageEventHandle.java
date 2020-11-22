package cn.pys.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MessageEventHandle implements ApplicationListener<MessageEvent>{

    @Override
    @Async
    public void onApplicationEvent(MessageEvent messageEvent) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("---------监听到event事件");
    }
}
