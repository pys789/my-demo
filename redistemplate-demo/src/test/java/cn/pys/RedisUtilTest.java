package cn.pys;

import cn.pys.utils.RedisTemplateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisUtilTest extends BaseTest {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Test
    public void testSet(){
        redisTemplateUtil.set("a", "1");
    }
}
