package com.example.moiza.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
class RedisConfig {

    @Value("\${spring.data.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.data.redis.port}")
    var redisPort: Int = 0

    @Value("\${spring.data.redis.password}")
    var redisPassword: String? = null

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration(redisHost, redisPort)
        redisPassword?.takeIf { it.isNotBlank() }?.let { config.setPassword(it) }
        return LettuceConnectionFactory(config)
    }
}
