package com.example.moiza.domain.auth.service

import com.example.moiza.global.config.properties.AuthProperties
import org.springframework.stereotype.Service

@Service
class GoogleLinkService(
        private val authProperties: AuthProperties
) {
    companion object {
        const val QUERY_STRING: String = "?client_id=%s&redirect_uri=%s" +
            "&response_type=token&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"
    }

    fun execute() = authProperties.google.baseUrl +
            String.format(
                    QUERY_STRING,
                    authProperties.google.clientId,
                    authProperties.google.redirectUrl)
}
