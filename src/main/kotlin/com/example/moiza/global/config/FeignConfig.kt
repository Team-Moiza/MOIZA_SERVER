package com.example.moiza.global.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.example.moiza.global.feign"])
@Configuration
class FeignConfig {
}