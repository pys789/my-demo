package cn.pys.service.impl;

import cn.pys.service.RedisRedPacketService;
import cn.pys.vo.UserRedPacket;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RedisRedPacketServiceImpl implements RedisRedPacketService {

    private static final String PREFIX = "red_packet_list_";
    /**
     * 每次取出1000条数据
     */
    private static final int TIME_SIZE = 1000;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
        log.info("----------开始保存数据----------------");
        long start = System.currentTimeMillis();
        BoundListOperations ops = redisTemplate.boundListOps(PREFIX + redPacketId);
        Long SIZE = ops.size();
        Long times = SIZE % TIME_SIZE == 0 ? SIZE / TIME_SIZE : (SIZE / TIME_SIZE + 1);

        int count = 0;
        List<UserRedPacket> userRedPackets = new ArrayList<>(TIME_SIZE);
        for (int i = 0; i < times; i++) {
            List userIdList = null;
            if (i == 0) {
                userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPackets.clear();
            for (int j = 0; j < userIdList.size(); j++) {
                String values = userIdList.get(j).toString();
                String[] arr = values.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                long userId = Long.parseLong(userIdStr);
                long time = Long.parseLong(timeStr);

                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setGrabTime(new Date(time));
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setNote("抢红包 " + redPacketId);
                userRedPackets.add(userRedPacket);
            }
            // 可以做数据库保存操作，然后从redis中删除缓存
            //count+=executeBatch(userRedPackets);
        }
        redisTemplate.delete(PREFIX + redPacketId);
        long end = System.currentTimeMillis();
        System.out.println("保存数据结束，耗时" + (end - start) + "毫秒，共" + count + "条记录被保存");
    }


}