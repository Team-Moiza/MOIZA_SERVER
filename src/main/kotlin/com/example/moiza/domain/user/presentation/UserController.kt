package com.example.moiza.domain.user.presentation

import com.example.moiza.domain.user.presentation.dto.req.RegisterProfileRequest
import com.example.moiza.domain.user.presentation.dto.req.UpdateProfileRequest
import com.example.moiza.domain.user.service.*
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/users")
@RestController
class UserController(
    private val profileService: ProfileService,
    private val getPortfolioPDFService: GetPortfolioPDFService,
    private val updateProfileService: UpdateProfileService,
    private val registerProfileService: RegisterProfileService,
    private val updatePictureService: UpdatePictureService,
    private val dropUserService: DropUserService,
) {
    @GetMapping
    fun findMyInfo()
        = profileService.execute()

    @GetMapping("/pdf/{portfolio-id}")
    fun getPortfolioPDF(@PathVariable("portfolio-id") portfolioId: Long): ResponseEntity<ByteArray> {
        val result = getPortfolioPDFService.execute(portfolioId)
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .body(result)
    }

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