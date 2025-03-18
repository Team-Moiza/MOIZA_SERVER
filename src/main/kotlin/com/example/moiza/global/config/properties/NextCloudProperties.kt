package com.example.moiza.global.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("nextcloud")
data class NextCloudProperties(
        val baseUrl: String,
        val username: String,
        val password: String,
)