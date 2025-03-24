package com.example.moiza.global.feign.nextcloud

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "nextcloud-upload", url = "\${nextcloud.base-url}")
interface NextcloudUploadClient {

    @PutMapping(
        value = ["/remote.php/dav/files/moiza/{fileName}"],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    fun uploadFile(
        @PathVariable("fileName") fileName: String?,
        @RequestBody fileData: ByteArray?,
        @RequestHeader("Authorization") authHeader: String?
    )
}