package cn.pys.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description
 * @Date 2020/12/10 9:47
 * @Created by pengys
 */
@Getter
@Setter
public class UserRedPacket {
    private Long redPacketId;
    private Long userId;
    private Date grabTime;
    private Double amount;
    private String note;
}
