package com.example.moiza.domain.auth.presentation

import com.example.moiza.domain.auth.presentation.dto.req.TokenRequest
import com.example.moiza.domain.auth.service.GetAccessTokenService
import com.example.moiza.domain.auth.service.GoogleAuthService
import com.example.moiza.domain.auth.service.GoogleLinkService
import com.example.moiza.domain.auth.service.LogoutService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController(
    private val googleLinkService: GoogleLinkService,
    private val googleAuthService: GoogleAuthService,
    private val getAccessTokenService: GetAccessTokenService,
    private val logoutService: LogoutService,
) {
    @GetMapping
    fun getGoogleAuthLink() = googleLinkService.execute()

    @PostMapping
    fun login(@RequestBody @Valid request: TokenRequest)
            = googleAuthService.execute(request.token)

    @PostMapping("/refresh")
    fun createNewAccessToken(@RequestBody @Valid request: TokenRequest)
            = getAccessTokenService.execute(request.token)

    @DeleteMapping
    fun logout(@RequestBody @Valid request: TokenRequest)
            = logoutService.execute(request.token)
}