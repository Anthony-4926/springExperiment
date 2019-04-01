package com.example.springboot_ex_04.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * @author 赵鑫
 * @create 2019-04-01 10:10
 */
@Service
@Slf4j
public class UserService {
    public void buyCar() {
        try {
            sleep(new Random().nextInt(9000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("buyCar():买了一辆豪华小轿车，老开心了！！！！！");
    }

}
