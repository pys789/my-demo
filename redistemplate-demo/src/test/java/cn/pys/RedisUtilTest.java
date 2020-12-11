package cn.pys;

import cn.pys.config.Contants;
import cn.pys.utils.RedisTemplateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class RedisUtilTest extends BaseTest {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSet(){
        redisTemplateUtil.set("a", "1");
        String a = redisTemplateUtil.get("a");
        System.out.println(a);
        redisTemplateUtil.put("red_packet_1","unit_amount",20);
        Object hashKey = redisTemplateUtil.getHashKey("red_packet_1", "unit_amount");
        System.out.println(hashKey);
        Object website = redisTemplateUtil.getHashKey("website", "google ");
        System.out.println(website);
    }

    @Test
    public void testList(){
        redisTemplateUtil.rightPush("red_packet_list_3","1-123");
        redisTemplateUtil.rightPush("red_packet_list_3","2-123");
        redisTemplateUtil.rightPush("red_packet_list_3","3-123");

        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(Contants.USER_RED_PACKET_PREFIX + "3");
        List<Object> list = ops.range(0, 3);
        System.out.println(list);
    }
}
