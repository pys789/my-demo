package cn.pys.service;

import cn.pys.model.User;

public interface UserService {

    int insert(User record);

    int updateById(User record);

    int deleteById(Integer id);

    User getById(Integer id);

}
