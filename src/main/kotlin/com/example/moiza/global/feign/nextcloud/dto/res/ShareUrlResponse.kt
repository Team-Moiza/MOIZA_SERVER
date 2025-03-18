package com.example.moiza.global.feign.nextcloud.dto.res

data class ShareUrlResponse(
    val ocs: OcsElement,
) {
    fun getUrl() = ocs.data.url
}

data class OcsElement(
    val data: DataElement,
)

data class DataElement(
    val url: String,
)