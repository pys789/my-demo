package cn.pys;

import cn.pys.entity.User;
import cn.pys.mapper.UserMapper;
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
        user.setName("pys");
        user.setAge(21);
        user.setEmail("test@abc.com");
        userMapper.insert(user);
    }

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
