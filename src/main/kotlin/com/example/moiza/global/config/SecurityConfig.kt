package com.example.moiza.global.config

import com.example.moiza.global.security.jwt.JwtTokenFilter
import com.example.moiza.global.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtProvider: JwtTokenProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .cors( Customizer.withDefaults() )

        http
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        http
            .addFilterBefore (
                JwtTokenFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )

        http
            .authorizeHttpRequests { authorize ->
                // auth
                authorize.requestMatchers("/auth/**").permitAll()

                // portfolio
                authorize.requestMatchers(HttpMethod.GET, "/portfolio").permitAll()

                authorize.anyRequest().authenticated()
            }

        http
            .exceptionHandling { handler ->
                handler.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            }

        return http.build()
    }
}