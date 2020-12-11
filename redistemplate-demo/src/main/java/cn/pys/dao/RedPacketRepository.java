package cn.pys.dao;

import cn.pys.entity.RedPacket;
import cn.pys.entity.User;
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
    @Query("update RedPacket r set r.stock = r.stock - 1 where r.id=:id")
    int reduceStock(@Param("id") Integer id);

}
