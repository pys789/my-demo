package cn.pys.service.impl;

import cn.pys.entity.Order;
import cn.pys.mapper.OrderMapper;
import cn.pys.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pys
 * @since 2020-11-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
