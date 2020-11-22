package cn.pys.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Date 2020/11/11 15:31
 * @Created by pengys
 */
@Data
public class UserOrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String name;

    private Integer goodsId;

    private String goodsName;

    private Integer dealNum;
}
