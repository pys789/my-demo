package cn.pys.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;

/**
 * @Description
 * @Date 2020/12/8 15:12
 * @Created by pengys
 */
@Data
@Entity
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    @Indexed
    private Integer userId;

    @Column(name = "name")
    private String name;
}
