package cn.pys.simple;

import redis.clients.jedis.Jedis;

/**
 * @Description
 * @Date 2020/11/18 17:36
 * @Created by pengys
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        //jedis.auth("");
        jedis.set("name2","pys2");
        String name = jedis.get("name2");
        System.out.println(name);
    }
}
