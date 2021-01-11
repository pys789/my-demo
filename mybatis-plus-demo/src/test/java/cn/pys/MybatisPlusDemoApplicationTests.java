package cn.pys;

import cn.pys.entity.FieldType;
import cn.pys.entity.User;
import cn.pys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testAdd() {
        System.out.println(("----- qdd test ------"));
        User user = new User();
        user.setUserId(4);
        // 可以直接将枚举类型的数据存入字符类型字段中
        user.setName(FieldType.BIZ_FIELD);
       // user.setAge(21);
        //user.setEmail("test@abc.com");
        userMapper.insert(user);
    }

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        User user = new User();
        user.setUserId(4);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(user);
        List<User> userList = userMapper.selectList(queryWrapper);

        User ret = userList.get(0);
        System.out.println(ret.getName().getCode());
        System.out.println(ret.getName().getDescription());
    }

}
