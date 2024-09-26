package com.example.moiza.global.security.principal

import com.example.moiza.domain.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val user: User
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority("ROLE_" + user.authority))
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.email
    }
}