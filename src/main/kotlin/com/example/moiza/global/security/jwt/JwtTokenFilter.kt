package com.example.moiza.global.security.jwt

import com.example.moiza.global.error.ErrorResponse
import com.example.moiza.global.error.exception.BusinessException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtTokenFilter(
    private val jwtProvider: JwtTokenProvider
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token: String? = jwtProvider.resolveToken(request)
            token?.let {
                val authentication: Authentication = jwtProvider.authorization(token)
                SecurityContextHolder.getContext().authentication = authentication
            }

            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            handleException(response, e)
        }

    }

    private fun handleException(response: HttpServletResponse, e: BusinessException) {
        val errorCode = e.errorCode
        val res = ErrorResponse(
            errorCode.status, errorCode.message
        )

        response.characterEncoding = "UTF-8"
        response.status = errorCode.status
        response.writer.write(res.toString())
    }
}