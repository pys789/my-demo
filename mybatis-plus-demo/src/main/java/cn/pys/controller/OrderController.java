package cn.pys.controller;


import cn.pys.mapper.UserOrderMapper;
import cn.pys.vo.UserOrderVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pys
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @RequestMapping("/get/{userId}")
    public String getUserOrder(@PathVariable("userId") Integer userId) {
        List<UserOrderVO> list = userOrderMapper.selectUserOrder(userId);
        return list == null ? "" : list.toString();
    }

    @RequestMapping("/wrapper/{userId}")
    public String wrapper(@PathVariable("userId") Integer userId) {
        QueryWrapper<UserOrderVO> wrapper = new QueryWrapper<>();
        wrapper.eq("a.user_id", userId);
        List<UserOrderVO> list = userOrderMapper.selectWrapperUserOrder(wrapper);
        return list == null ? "" : list.toString();
    }

}
