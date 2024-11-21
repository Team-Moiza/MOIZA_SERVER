package com.example.moiza.domain.user.presentation

import com.example.moiza.domain.user.presentation.dto.req.UpdateProfileRequest
import com.example.moiza.domain.user.service.ProfileService
import com.example.moiza.domain.user.service.UpdateProfileService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val profileService: ProfileService,
    private val updateProfileService: UpdateProfileService,
) {
    @GetMapping
    fun findMyInfo()
        = profileService.execute()

    @PatchMapping("/update")
    fun updateProfile(@RequestBody @Valid request: UpdateProfileRequest)
        = updateProfileService.execute(request)
}