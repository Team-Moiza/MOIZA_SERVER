package com.example.moiza.domain.auth.persentation

import com.example.moiza.domain.auth.persentation.dto.req.AccessTokenRequest
import com.example.moiza.domain.auth.persentation.dto.req.RefreshTokenRequest
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
    fun login(@RequestBody @Valid accessTokenRequest: AccessTokenRequest)
            = googleAuthService.execute(accessTokenRequest.accessToken)

    @PostMapping("/refresh")
    fun createNewAccessToken(@RequestBody @Valid request: RefreshTokenRequest)
            = getAccessTokenService.execute(request.refreshToken)

    @DeleteMapping
    fun logout(@RequestBody @Valid refreshTokenRequest: RefreshTokenRequest)
            = logoutService.execute(refreshTokenRequest.refreshToken)
}