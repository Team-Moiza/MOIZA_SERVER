package com.example.moiza.global.feign

import com.example.moiza.global.feign.auth.dto.res.GoogleInformationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GoogleInformationClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleInformationClient {
    @GetMapping("?alt=json")
    fun getInformation(@RequestParam access_token: String): GoogleInformationResponse
}