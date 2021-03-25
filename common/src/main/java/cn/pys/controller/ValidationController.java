package cn.pys.controller;

import cn.pys.entity.UserEntity;
import cn.pys.exception.Res;
import cn.pys.group.AddGroup;
import cn.pys.group.UpdateGroup;
import cn.pys.utils.ValidatorUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description
 * @Date 2020/11/12 15:14
 * @Created by pengys
 */
@RestController
@RequestMapping("/valid")
public class ValidationController {

    @PostMapping("/save/user")
    public Res saveUser(@Valid @RequestBody UserEntity user) {
        // 真实操作 ...
        return Res.ok();
    }

    @PostMapping("/update/user")
    public Res updateUser(@Valid @RequestBody UserEntity user) {
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
