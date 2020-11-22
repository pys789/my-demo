package cn.pys.controller;

import cn.pys.entity.UserEntity;
import cn.pys.exception.Res;
import cn.pys.group.AddGroup;
import cn.pys.group.UpdateGroup;
import cn.pys.utils.ValidatorUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Date 2020/11/12 15:14
 * @Created by pengys
 */
@RestController
@RequestMapping("/valid")
public class ValidationController {

    @PostMapping("/save/user")
    public Res saveUser(@RequestBody UserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        // 真实操作 ...
        return Res.ok();
    }

    @PostMapping("/update/user")
    public Res updateUser(@RequestBody UserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        // 真实操作 ...
        return Res.ok();
    }

    @GetMapping("/get/exception")
    public Res getException() {
        //throw new ResException("自定义异常", 501);
        int a = 1 / 0;
        return Res.ok();
    }

}
