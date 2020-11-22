package cn.pys.controller;

import cn.pys.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Tuple;

import java.util.*;

@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("getRank")
    public Object getRank() {
        List<Map<String, Object>> list = new ArrayList<>();
        Set<Tuple> rank = redisUtil.zrangeWithScores("last_week_rank", 0, 9);
        for (Tuple tuple : rank) {
            Map<String, Object> map = new HashMap<>();
            map.put("postId", tuple.getElement());
            map.put("score",tuple.getScore());
            map.put("title", redisUtil.hget("rank_post_" + tuple.getElement(), "title"));
            list.add(map);
        }
        return list;
    }
}
