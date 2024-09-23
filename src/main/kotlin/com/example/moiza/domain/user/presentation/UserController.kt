package com.example.moiza.domain.user.presentation

import com.example.moiza.domain.user.service.ProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val profileService: ProfileService,
) {
    @GetMapping
    fun findMyInfo()
        = profileService.execute()
}