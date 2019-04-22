package com.example.springboot_xe_07.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 赵鑫
 * @create 2019-04-22 10:26
 */

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
