package com.yss.redislock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wfy
 */
@Slf4j
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/redisLock")
    public String redisLockDealWith() {

        String lockKey = "RedisController_redisLockDealWith";
        String lockValue = UUID.randomUUID().toString();
        try {
            //上锁
            Boolean tryLock = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 10, TimeUnit.SECONDS);
            if (tryLock) {
                return "锁已存在";
            }
            //执行逻辑
            int stock = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("stock")));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            //解锁
            if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }

        return "END";
    }
}
