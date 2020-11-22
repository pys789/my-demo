package cn.pys.service.impl;

import cn.pys.mapper.UserMapper;
import cn.pys.model.User;
import cn.pys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @CacheEvict(value = "users", key = "#record.id")
    @Override
    public int updateById(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @CacheEvict(value = "users", key = "#id")
    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Cacheable(value = "users", key = "#id")
    @Override
    public User getById(Integer id) {
        System.out.println("查库了");
        return userMapper.selectByPrimaryKey(id);
    }
}
