package com.example.springboot_xe_07.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * @author 赵鑫
 * @create 2019-04-22 10:12
 */

@Slf4j
@Component
public class EncryptorComponent {
    @Value("${my.secretkey}")
    private String secrekey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private ObjectMapper mapper;

    /**
     * 加密
     *
     * @param payload
     * @return
     */
    public String encrypt(Map payload) {
        try {
            String json = mapper.writeValueAsString(payload);
            return Encryptors.text(secrekey, salt).encrypt(json);
        } catch (JsonProcessingException e) {

        }
        return null;
    }


    /**
     * 解密
     * @param encryptString
     * @return
     */
    public Map<String, Object> decrypt(String encryptString) {
        try {
            String json = Encryptors.text(secrekey, salt).decrypt(encryptString);
            return mapper.readValue(json, Map.class);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录");
        }

    }


}
