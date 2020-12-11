package cn.pys.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description
 * @Date 2020/12/10 9:47
 * @Created by pengys
 */
@Data
@Entity
@Table(name = "t_red_packet")
public class RedPacket{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "unit_amount")
    private Integer unitAmount;

}
