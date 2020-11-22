package cn.pys.mapper;

import cn.pys.vo.UserOrderVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Date 2020/11/11 15:30
 * @Created by pengys
 */
public interface UserOrderMapper{

    List<UserOrderVO> selectUserOrder(@Param("userId") Integer userId);

    /**
     * 自定义sql使用wrapper，也可以将sql放到 xml 中
     *
     * @param wrapper
     * @return
     */
    @Select("SELECT b.user_id,b.name,c.goods_id,c.goods_name,a.deal_num FROM t_order a " +
            "LEFT JOIN t_user b on a.user_id=b.user_id " +
            "LEFT JOIN t_goods c on a.goods_id=c.goods_id " +
            "${ew.customSqlSegment}")
    List<UserOrderVO> selectWrapperUserOrder(@Param(Constants.WRAPPER) Wrapper wrapper);
}
