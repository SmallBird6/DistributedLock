package com.yss.redisson;

import com.yss.redislock.RedisThreadLocal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wfy
 */
@Aspect
@Component
public class RedissonLockDealWith {

    @Autowired
    private Redisson redisson;

    @Pointcut("@annotation(com.yss.redisson.RedissonLock)")
    public void redisPointCut() {}

    @Before("redisPointCut()")
    public void before(JoinPoint joinPoint) {
    }

    public String getRedisLockKey(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        return className + "_" + methodName;
    }


}
