package cn.pys.service;

import cn.pys.dao.UserRepository;
import cn.pys.entity.User;
import cn.pys.utils.RedisTemplateUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Resource
    private EhCacheCacheManager ehCacheCacheManager;

    @Resource
    private RedisTemplateUtil redisTemplateUtil;

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

    @Cacheable(value = "users", unless = "#result==null", key = "'allUsers'")
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

    @Cacheable(value = "local", key = "'local_user'")
    public User local() {
        log.info("进入local缓存方法");
        User user = new User();
        user.setId(99L);
        user.setUserId(11);
        user.setName("local");
        return user;
    }


    /**
     * 测试将缓存同时放入ehcache和redis
     */
    public String both() {
        log.info("进入local缓存方法");
        Cache local = ehCacheCacheManager.getCacheManager().getCache("local");
        Element both = local.get("both");
        if (both != null) {
            Object objectValue = both.getObjectValue();
            return objectValue.toString();
        }
        String redisData = redisTemplateUtil.get("both");
        if (StringUtils.hasLength(redisData)) {
            return redisData;
        }
        // 模拟数据查询
        User user = new User();
        user.setId(101L);
        user.setUserId(12);
        user.setName("both");

        // 放入ehcache和redis
        local.put(new Element("both", user));
        redisTemplateUtil.set("both", JSONObject.toJSONString(user));
        return JSONObject.toJSONString(user);
    }
}
