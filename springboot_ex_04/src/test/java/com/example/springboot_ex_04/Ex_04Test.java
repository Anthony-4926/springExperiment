package com.example.springboot_ex_04;

import com.example.springboot_ex_04.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 赵鑫
 * @create 2019-04-01 10:30
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Ex_04Test {

    @Autowired
    private UserService userService;


    @Test
    public void test() {
        userService.buyCar();
    }

    @Test
    public void test1() {
        userService.addUser();
    }


}
