package com.example.springboot_ex_05.entity;

import com.example.springboot_ex_05.controller.UserController;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 赵鑫
 * @create 2019-04-08 10:26
 */

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int uid;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String detail;




}
