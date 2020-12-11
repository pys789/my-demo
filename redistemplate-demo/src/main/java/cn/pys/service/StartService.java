package cn.pys.service;

import cn.pys.config.Contants;
import cn.pys.dao.RedPacketRepository;
import cn.pys.entity.RedPacket;
import cn.pys.utils.RedisTemplateUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {

    @Resource
    RedPacketRepository redPacketRepository;

    @Resource
    private RedisTemplateUtil redisTemplateUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化红包
        List<RedPacket> list = redPacketRepository.findAll();
        if (CollectionUtils.isEmpty(list)) return;
        for (RedPacket redPacket : list) {
            redisTemplateUtil.put(Contants.RED_PACKET_PREFIX + redPacket.getId(), "stock", redPacket.getStock());
            redisTemplateUtil.put(Contants.RED_PACKET_PREFIX + redPacket.getId(), "unit_amount", redPacket.getUnitAmount());
        }

    }


}