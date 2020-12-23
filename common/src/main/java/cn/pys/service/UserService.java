package cn.pys.service;

import cn.pys.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2020/12/23 10:15
 * @Created by pengys
 */
@Service
public class UserService {

    @Cacheable(value="users")
    public User selectUserById(int id) {
        System.out.println("访问缓存了");
        return new User(1,"pys",20);
    }
}
