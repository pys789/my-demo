package cn.pys.service;

import cn.pys.dao.UserRepository;
import cn.pys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description
 * @Date 2020/12/8 15:22
 * @Created by pengys
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private UserRepository userRepository;

    @CachePut(value = "user", unless = "#result==null", key = "#userId")
    public User saveUser(Integer userId, String name) {
        log.info("进入saveUser方法");
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        return userRepository.save(user);
    }

    /**
     * JPA中修改和删除必须添加事务
     */
    @CacheEvict(value = "user", key = "'user_'+#userId")
    @Transactional
    public Integer updateByUserId(Integer userId, String name) {
        log.info("进入updateByUserId方法");
        return userRepository.updateByUserId(userId, name);
    }

    @Cacheable(value = "user", unless = "#result==null", key = "'user_'+#userId")
    public User getUserById(Integer userId) {
        log.info("进入getUserById方法");
        return userRepository.findByUserId(userId);
    }

    @Cacheable(value = "users",unless = "#result==null")
    public List<User> getAll() {
        log.info("进入getAll方法");
        return userRepository.findAll();
    }

    @CacheEvict(value = "user", key = "'user_'+#userId")
    @Transactional
    public int deleteUser(Integer userId) {
        log.info("进入deleteUser方法");
        return userRepository.deleteByUserId(userId);
    }
}
