package com.abing.ratelimiter.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimiterAop {

    //定义切入点 拦截com.abing.retelimiter
    @Pointcut("execution(public * com.abing.ratelimiter.*.*(..))")
    public void rlApp(){

    }
}
