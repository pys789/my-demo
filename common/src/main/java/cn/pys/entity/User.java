package cn.pys.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 6589514995751474458L;
    private Integer id;
    private String name;
    private Integer age;

     
}