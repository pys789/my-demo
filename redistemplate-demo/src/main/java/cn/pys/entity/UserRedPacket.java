package cn.pys.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Date 2020/12/10 9:47
 * @Created by pengys
 */
@Data
@Entity
@Table(name = "t_user_red_packet")
public class UserRedPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "red_packet_id")
    private Integer redPacketId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "grab_time")
    private Date grabTime;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "note")
    private String note;
}
