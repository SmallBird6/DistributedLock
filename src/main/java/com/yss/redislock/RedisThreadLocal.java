package com.yss.redislock;

/**
 * @author wfy
 */
public class RedisThreadLocal {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String uuidValue) {
        threadLocal.set(uuidValue);
    }

    public static String get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
