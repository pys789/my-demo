package cn.pys.dao;

import cn.pys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Date 2020/12/8 15:17
 * @Created by pengys
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
