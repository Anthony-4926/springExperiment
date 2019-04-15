package com.example.springboot_ex_06.controller;

import com.example.springboot_ex_06.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author 赵鑫
 * @create 2019-04-15 10:36
 */

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @PostMapping("/users")
    public Map postUser(@Valid @RequestBody User user){
        return Map.of("user",user);
    }

    @GetMapping("/users/{username}")
    public void getViolatonException(@Size(min = 2, max = 5, message = "用户参数信息错误")
                                     @PathVariable String userName) {

    }

}
