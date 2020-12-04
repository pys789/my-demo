package cn.pys;

import cn.pys.utils.JedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisUtilTest extends BaseTest{

    @Autowired
    private JedisUtil redisUtil;

    @Test
    public void testSet(){
        System.out.println(redisUtil.set("b", "2"));

    }
}
