package cn.pys.config;

import cn.pys.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1000)
@Component
public class ContextStartup implements ApplicationRunner{

    @Autowired
    private PostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // postService.initIndexWeekRank();
    }
}
