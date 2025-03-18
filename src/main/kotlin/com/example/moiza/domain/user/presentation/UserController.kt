package com.example.moiza.domain.user.presentation

import com.example.moiza.domain.user.presentation.dto.req.RegisterProfileRequest
import com.example.moiza.domain.user.presentation.dto.req.UpdateProfileRequest
import com.example.moiza.domain.user.service.*
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/users")
@RestController
class UserController(
    private val profileService: ProfileService,
    private val updateProfileService: UpdateProfileService,
    private val registerProfileService: RegisterProfileService,
    private val updatePictureService: UpdatePictureService,
    private val dropUserService: DropUserService,
) {
    @GetMapping
    fun findMyInfo()
        = profileService.execute()

    @PatchMapping("/register")
    fun updateProfile(@RequestBody @Valid request: RegisterProfileRequest)
        = registerProfileService.execute(request)

    @PatchMapping("/update")
    fun updateProfile(@RequestBody @Valid request: UpdateProfileRequest)
        = updateProfileService.execute(request)

    @PatchMapping("/picture")
    fun updatePicture(@RequestParam("file") file: MultipartFile) {
        updatePictureService.execute(file)
    }

    @DeleteMapping
    fun dropUser()
        = dropUserService.execute()
}