package com.example.moiza.global.feign.nextcloud

import com.example.moiza.global.feign.nextcloud.dto.res.ShareUrlResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "nextcloud-share", url = "\${nextcloud.base-url}")
interface NextcloudShareClient {

    @PostMapping("/ocs/v2.php/apps/files_sharing/api/v1/shares")
    fun createPublicShare(
        @RequestHeader("Authorization") authHeader: String?,
        @RequestHeader("OCS-APIRequest") ocsHeader: String?,
        @RequestHeader("Accept") acceptHeader: String?,
        @RequestParam("path") path: String?,
        @RequestParam("shareType") shareType: Int,
        @RequestParam("permissions") permissions: Int
    ): ShareUrlResponse
}