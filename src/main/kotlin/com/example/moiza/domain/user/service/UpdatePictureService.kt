package com.example.moiza.domain.user.service

import com.example.moiza.domain.user.facade.UserFacade
import com.example.moiza.global.utils.nextcloud.NextCloudService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class UpdatePictureService(
    private val userFacade: UserFacade,
    private val nextCloudService: NextCloudService,
) {
    @Transactional
    fun execute(file: MultipartFile) {
        val user = userFacade.getCurrentUser()

        val profileImg = nextCloudService.uploadFile(file, user.id)
        user.updateProfileImg(profileImg)
    }
}