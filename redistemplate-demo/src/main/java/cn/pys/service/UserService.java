package cn.pys.service;

import cn.pys.dao.UserRepository;
import cn.pys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public User saveUser(Integer userId,String name){
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        return userRepository.save(user);
    }
}
