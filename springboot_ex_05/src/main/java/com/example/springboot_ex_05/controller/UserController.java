package com.example.springboot_ex_05.controller;

import com.example.springboot_ex_05.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 赵鑫
 * @create 2019-04-08 10:18
 */

@Slf4j
@RestController
@RequestMapping("/api")
@Setter
@Getter
public class UserController {
    private static List<User> users= creatUsers();


    @GetMapping("/index")
    public Map getIndex() {
        return Map.of("index", "main");
    }

    @GetMapping("/users")
    public Map getUsers() {
        return Map.of("users", users);
    }

    @GetMapping("/user/{uid}")
    public Map getUser(@PathVariable int uid) {
        log.debug("{}", uid);
        User user = users.stream()
                .filter(u -> u.getUid()==uid)
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(user)
                .map(u -> Map.of("user", u))
                .orElse(Map.of());
    }

    @PostMapping("/users")
    public Map postUser(@RequestBody User user) {
        addUser(user);
        users.forEach(u->{
            log.debug(u.getUserName());
        });
        return Map.of("users", users);
    }



    public static List<User> creatUsers() {
        users=new ArrayList<>();
//        模拟了十个用户
        for(int i=0; i<10; i++){
            users.add(new User(i,"张"+i, "000000"+i, "address"+i));
        }
        return users;
    }

    public static void addUser(User user){
        log.debug("{}", user);
        Optional.ofNullable(user)
                .ifPresent(u->{
                    u.setUid(users.size());
                    users.add(user);});
    }
}
