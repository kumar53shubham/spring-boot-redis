package com.spring.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory(){
//        host and port
//        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
//        configuration.setHostName("localhost");
//        configuration.setPort(6379);

//        we are using the default jedis configuration
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<Object,Object> template(){
        RedisTemplate<Object,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        return template;
    }
}
