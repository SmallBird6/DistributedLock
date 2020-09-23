package com.yss.configuration;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wfy
 */
@Configuration
public class RedissonConfiguration {

    @Value("${redisson.redis.address}")
    private String reddsionAddress;

    @Value("${redisson.redis.password}")
    private String password;

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(reddsionAddress)
                .setPassword(password);
        return (Redisson) Redisson.create(config);
    }

}
