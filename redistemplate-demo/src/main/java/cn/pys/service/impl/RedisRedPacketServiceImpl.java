package cn.pys.service.impl;

import cn.pys.dao.RedPacketRepository;
import cn.pys.dao.UserRedPacketRepository;
import cn.pys.entity.UserRedPacket;
import cn.pys.service.RedisRedPacketService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RedisRedPacketServiceImpl implements RedisRedPacketService {

    private static final String PREFIX = "red_packet_list_";
    /**
     * 每次取出100条数据
     */
    private static final int TIME_SIZE = 100;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    RedPacketRepository redPacketRepository;

    @Resource
    UserRedPacketRepository userRedPacketRepository;

    /**
     * 注解＠Async 表示让 Spring 自动创建另外一条线程去运行它，这样它便不在抢最后一个红包的线程之内。
     * 因为这个方法是一个较长时间的方法，在同一个线程内，那么对于最后抢红包的用户需要等待的时间太长，用户体验不好
     */
    @Async
    public void saveUserRedPacketByRedis(Integer redPacketId, Double unitAmount) {
        log.info("----------开始保存数据----------------");
        long start = System.currentTimeMillis();
        BoundListOperations ops = redisTemplate.boundListOps(PREFIX + redPacketId);
        Long SIZE = ops.size();
        if (SIZE == null || SIZE == 0) return;
        long times = SIZE % TIME_SIZE == 0 ? SIZE / TIME_SIZE : (SIZE / TIME_SIZE + 1);

        List<UserRedPacket> userRedPackets = new ArrayList<>(TIME_SIZE);
        for (int i = 0; i < times; i++) {
            List userIdList;
            if (i == 0) {
                userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            if (CollectionUtils.isEmpty(userIdList)) return;
            userRedPackets.clear();
            for (int j = 0; j < userIdList.size(); j++) {
                String values = userIdList.get(j).toString();
                String[] arr = values.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                int userId = Integer.parseInt(userIdStr);
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
            for (UserRedPacket userRedPacket : userRedPackets) {
                try {
                    save2DB(userRedPacket);
                } catch (Exception ex) {
                    log.info("保存记录出现异常,userRedPacket={}", JSONObject.toJSONString(userRedPacket));
                }
            }
        }
        redisTemplate.delete(PREFIX + redPacketId);
        long end = System.currentTimeMillis();
        System.out.println("保存数据结束，耗时" + (end - start) + "毫秒");
    }

    @Transactional(rollbackOn = Exception.class)
    public void save2DB(UserRedPacket userRedPacket) {
        redPacketRepository.reduceStock(userRedPacket.getRedPacketId());
        userRedPacketRepository.save(userRedPacket);
    }


}