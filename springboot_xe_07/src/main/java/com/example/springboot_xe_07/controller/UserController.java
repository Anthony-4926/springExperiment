package com.example.springboot_xe_07.controller;

import com.example.springboot_xe_07.component.EncryptorComponent;
import com.example.springboot_xe_07.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 赵鑫
 * @create 2019-04-22 10:29
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private Map<String, User> users = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;

    @RequestMapping("/register")
    public Map register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.put(user.getUserName(), user);
        return Map.of("user", user);
    }

    @PostMapping("login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(users.get(user.getUserName()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户不存在或密码错误");
                })
                .ifPresent(u -> {
                    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户不存在或密码错误");
                    }
                    Map map = Map.of("name", u.getUserName());
                    String token = encryptorComponent.encrypt(map);
                    response.setHeader("Anthorization", token);
                });
    }

    @GetMapping("/index")
    public Map getIndex(@RequestAttribute String name) {
        log.debug(name);
        return Map.of("用户真实姓名", name);
    }

}
