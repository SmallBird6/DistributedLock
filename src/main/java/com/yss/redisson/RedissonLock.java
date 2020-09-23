package com.yss.redisson;

import java.lang.annotation.*;

/**
 * @author wfy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonLock {
}
