package cn.pys.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;
    //加密密码的盐,暂设成跟用户名一致
    private String salt;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}