package com.example.moiza.global.utils.nextcloud

import com.example.moiza.global.config.properties.NextCloudProperties
import com.example.moiza.global.feign.nextcloud.NextcloudShareClient
import com.example.moiza.global.feign.nextcloud.NextcloudUploadClient
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.util.*

@Component
class NextCloudService(
    private val nextCloudProperties: NextCloudProperties,
    private val nextcloudShareClient: NextcloudShareClient,
    private val nextcloudUploadClient: NextcloudUploadClient,
) {
    fun uploadFile(
        file: MultipartFile,
        userId: Long,
    ): String {
        val fileName = FILE_NAME_TEMPLATE
            .format(UUID.randomUUID(), userId, file.originalFilename)
        val fileData = file.bytes

        return requestNextCloud(fileName, fileData)
    }

    fun uploadFile(
        fileData: ByteArray,
        userId: Long,
    ): String {
        val fileName = FILE_NAME_TEMPLATE
            .format(UUID.randomUUID(), userId, LocalDate.now().toString()) + ".pdf"

        return requestNextCloud(fileName, fileData)
    }

    private fun requestNextCloud(fileName: String, fileData: ByteArray): String {
        val authHeader = "Basic " + Base64.getEncoder()
            .encodeToString(
                (nextCloudProperties.username + ":" + nextCloudProperties.password)
                    .toByteArray(StandardCharsets.UTF_8)
            )

        nextcloudUploadClient.uploadFile(fileName, fileData, authHeader)

        val shareUrlResponse = nextcloudShareClient.createPublicShare(
            authHeader, "true", "application/json",
            "/$fileName", 3, 1
        )

        return (shareUrlResponse.getUrl()
            .replace(nextCloudProperties.baseUrl, "https://nas.anys.kro.kr")
                + "/download")
    }

    companion object {
        private const val FILE_NAME_TEMPLATE: String = "%s-%s-%s"
    }
}