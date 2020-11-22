package cn.pys;

import cn.pys.model.User;
import cn.pys.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

public class UserServiceTest extends BaseTest{

    @Resource
    UserService userService;

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUserId(1);
        user.setUserName("pys");
        user.setBirthday(new Date());
        userService.insert(user);

    }

}
