package cn.pys.controller;


import cn.pys.entity.Goods;
import cn.pys.service.IGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pys
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/add/{id}")
    public String add(@PathVariable("id") Integer id) {
        Goods goods = new Goods();
        goods.setGoodsId(id);
        goods.setGoodsName("name-" + id);
        goods.setBarcode("code-" + id);
        goods.setPrice(id + 0.0);
        goodsService.save(goods);
        return "OK";
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", id).last("limit 1");
        Goods one = goodsService.getOne(queryWrapper);
        return one == null ? "" : one.toString();
    }

}
