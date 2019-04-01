package com.example.springboot_ex_04.aspect;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 赵鑫
 * @create 2019-04-01 10:48
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE,METHOD})
public @interface MyInterceptor {
    AuthorityType[] value() default AuthorityType.User;

    public enum AuthorityType {
        User, ADMIN, SUPPERADMIN;
    }
}
