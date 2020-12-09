package cn.pys.simple;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

/**
 * @Description
 * @Date 2020/11/18 17:36
 * @Created by pengys
 */
public class RedisTest {

    public static void main(String[] args) {
        // Jedis是redis的原生工具类，没有实现连接池管理
        Jedis jedis = new Jedis("localhost", 6379);
        //jedis.auth("");
        jedis.set("name2", "pys2");
        //String load = "return redis.call('set',KEYS[1],ARGV[1])";
        //String load = jedis.scriptLoad("return redis.call('set',KEYS[1],ARGV[1])");
        //Object test = jedis.eval(load, Arrays.asList("a"), Arrays.asList("1"));
        //Object test = jedis.evalsha("c686f316aaf1eb01d5a4de1b0b63cd233010e63d", Arrays.asList("b"), Arrays.asList("2"));
        String name = jedis.get("name2");
        System.out.println(name);
    }
}
