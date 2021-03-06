package cn.pys.service;

/**
 * @Description
 * @Date 2020/12/10 10:21
 * @Created by pengys
 */
public interface UserRedPacketService {
    /**
     * 通过Redis实现抢红包
     *
     * @param redPacketId --红包编号
     * @param userId      -- 用户编号
     * @return 0-没有库存，失败 1--成功，且不是最后一个红包 2--成功，且是最后一个红包
     */
    Long grapRedPacketByRedis(Integer redPacketId, Long userId);
}
