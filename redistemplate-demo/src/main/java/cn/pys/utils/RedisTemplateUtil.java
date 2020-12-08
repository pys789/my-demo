package cn.pys.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RedisTemplateUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.info("set异常", e);
            return false;
        }
    }

    public String get(String key) {
        try {
            Object obj = redisTemplate.opsForValue().get(key);
            return obj == null ? "" : obj.toString();
        } catch (Exception e) {
            log.info("get异常", e);
            return null;
        }
    }


}
