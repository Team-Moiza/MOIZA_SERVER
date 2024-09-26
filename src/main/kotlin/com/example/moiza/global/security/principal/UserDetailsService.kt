package com.example.moiza.global.security.principal

import com.example.moiza.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val userFacade: UserFacade
): UserDetailsService {
    override fun loadUserByUsername(email: String)
            = AuthDetails(userFacade.getUserByEmail(email))
}