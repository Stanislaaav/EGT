package com.siliev.egt.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@EnableConfigurationProperties(CacheConfigurationProperties.class)
@Configuration
public class CacheConfig {

    private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(timeoutInSeconds));
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration(CacheConfigurationProperties properties) {
        return createCacheConfiguration(properties.getTimeoutSeconds());
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, CacheConfigurationProperties properties) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        for (Map.Entry<String, Long> cacheNameAndTimeout : properties.getCacheExpirations().entrySet()) {
            cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(cacheNameAndTimeout.getValue()));
        }

        return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(cacheConfiguration(properties))
            .withInitialCacheConfigurations(cacheConfigurations).build();
    }

}
