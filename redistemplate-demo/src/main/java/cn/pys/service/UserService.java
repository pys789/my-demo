package cn.pys.service;

import cn.pys.dao.UserRepository;
import cn.pys.entity.User;
import lombok.extern.slf4j.Slf4j;
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

    public User saveUser(Integer userId, String name) {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        return userRepository.save(user);
    }

    /**
     * JPA中修改和删除必须添加事务
     */
    @Transactional
    public Integer updateByUserId(Integer userId, String name) {
        return userRepository.updateByUserId(userId, name);
    }

    public User getUserById(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public int deleteUser(Integer userId) {
        return userRepository.deleteByUserId(userId);
    }
}
