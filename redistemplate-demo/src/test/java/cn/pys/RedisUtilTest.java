package cn.pys;

import cn.pys.utils.RedisTemplateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class RedisUtilTest extends BaseTest {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSet(){
        redisTemplateUtil.set("a", "1");

        Object hashKey = redisTemplateUtil.getHashKey("red_packet_1", "unit_amount");
        System.out.println(hashKey);
    }
}
