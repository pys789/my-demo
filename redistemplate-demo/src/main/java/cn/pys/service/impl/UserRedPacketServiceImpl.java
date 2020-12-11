package cn.pys.service.impl;

import cn.pys.service.RedisRedPacketService;
import cn.pys.service.UserRedPacketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    RedisRedPacketService redisRedPacketService;
    @Resource
    DefaultRedisScript<String> grapRedPackageRedisScript;

    @Override
    public Long grapRedPacketByRedis(Integer redPacketId, Long userId) {
        String args = userId + "-" + System.currentTimeMillis();
        String key = String.valueOf(redPacketId);

        Object res = redisTemplate.execute((RedisConnection connection) -> connection.eval(
                grapRedPackageRedisScript.getScriptAsString().getBytes(),
                ReturnType.INTEGER,
                1,
                key.getBytes(),
                args.getBytes()));
        Long result = (Long) res;
        if (result != null && result == 2) {
            // 红包抢完后，存入mysql
            Object unitAmountStr = redisTemplate.opsForHash().get("red_packet_" + redPacketId, "unit_amount");
            Double unitAmount = Double.valueOf(unitAmountStr == null ? "0" : unitAmountStr.toString());
            redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
        }
        return result;
    }
}