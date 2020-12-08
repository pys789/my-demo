package cn.pys.dao;

import cn.pys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Description
 * @Date 2020/12/8 15:17
 * @Created by pengys
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(Integer userId);

    int deleteByUserId(Integer userId);

    @Modifying
    @Query("UPDATE User u SET u.name = :name WHERE u.userId = :userId")
    int updateByUserId(@Param("userId") Integer userId, @Param("name")String name);
}
