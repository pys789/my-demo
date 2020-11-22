package cn.pys.service.impl;

import cn.pys.entity.Goods;
import cn.pys.mapper.GoodsMapper;
import cn.pys.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pys
 * @since 2020-11-10
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
