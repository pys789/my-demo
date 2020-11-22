package cn.pys.entity;

import cn.pys.contants.SqlCondition;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Conditional;

/**
 * @Description
 * @Date 2020/11/10 15:06
 * @Created by pengys
 */
@Data
@TableName(value = "t_user")
@EqualsAndHashCode(callSuper = false)
public class User {
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;
    private Integer userId;
    private String name;
    private Integer age;
    private String email;
}

