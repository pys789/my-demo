package cn.pys.service;

public interface RedisRedPacketService {
    /**
     * 保存redis抢红包列表
     * @param redPacketId
     * @param unitAmount
     */
    void saveUserRedPacketByRedis(Integer redPacketId, Double unitAmount);
}