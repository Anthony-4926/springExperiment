package com.example.springboot_ex_04.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 赵鑫
 * @create 2019-04-01 10:20
 */

@Slf4j
@Component
@Aspect
public class MyAspect {
    /**
     * @param joinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("execution(* com.example..*.buy*(..))")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        log.debug("方法{}()，执行时间{}",joinPoint.getSignature().getName(), end-start);
        return result;
    }

    /**
     *
     * @param joinPoint
     * @param interceptor
     * @return
     * @throws Throwable
     */
    @Around("@within(interceptor) || @annotation(interceptor)")
    public Object interceptorTarget(ProceedingJoinPoint joinPoint, MyInterceptor interceptor) throws Throwable {
        Optional.ofNullable(interceptor)
                .or(()->{
                    MyInterceptor m = joinPoint.getTarget().getClass().getAnnotation(MyInterceptor.class);
                    return Optional.of(m);
                }).ifPresent(m->{
            for(MyInterceptor.AuthorityType t: m.value()){
                log.debug("当前执行方法的权限：{}",t);
            }
        });
        return joinPoint.proceed();
    }

}
