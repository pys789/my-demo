package cn.pys.dao;

import cn.pys.entity.RedPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Description
 * @Date 2020/12/10 16:13
 * @Created by pengys
 */
public interface RedPacketRepository extends JpaRepository<RedPacket, Integer> {

    @Modifying
    @Query("UPDATE RedPacket r SET r.stock = 1 WHERE r.id=:id")
    void reduceStock(@Param("id") Integer id);

}
