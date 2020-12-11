package cn.pys.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Date 2020/12/10 9:47
 * @Created by pengys
 */
@Data
@Entity
@Table(name = "t_red_packet")
public class RedPacket implements Serializable {

    private static final long serialVersionUID = 1520308802666015024L;
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "stock")
    private Integer stock;

}
