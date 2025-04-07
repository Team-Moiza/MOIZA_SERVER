package com.example.moiza.domain.user.service

import com.example.moiza.domain.portfolio.domain.repository.PortfolioRepository
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.user.facade.UserFacade
import com.example.moiza.global.config.properties.NextCloudProperties
import com.example.moiza.global.utils.nextcloud.NextCloudService
import com.example.moiza.global.utils.thymeleaf.ProcessTemplateService
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.options.WaitUntilState
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Files


@Service
class GetPortfolioPDFService(
    private val userFacade: UserFacade,
    private val nextCloudService: NextCloudService,
    private val portfolioRepository: PortfolioRepository,
    private val processTemplateService: ProcessTemplateService,
    private val nextCloudProperties: NextCloudProperties,
) {
    @Transactional(readOnly = true)
    fun execute(id: Long): ByteArray {
        val user = userFacade.getCurrentUser()
        val portfolio = (portfolioRepository.findPortfolioByIdAndUser(id, user)
            ?: throw PortfolioNotFoundException)

        val profile = user.profile.replace("https://nas.anys.kro.kr", nextCloudProperties.baseUrl)

        val data = mapOf(
            "user" to user,
            "profile" to profile,
            "portfolio" to portfolio,
            "codes" to portfolio.codes.map { it.code },
        )

        val html = processTemplateService.execute("resume", data)

        val htmlPath = Files.createTempFile("resume-", ".html")
        Files.write(htmlPath, html.toByteArray(Charsets.UTF_8))
        val pdfBytes = Playwright.create().use { playwright ->
            val browser = playwright.chromium().launch(
                BrowserType.LaunchOptions().setHeadless(true)
            )
            val page = browser.newPage()
            page.navigate(htmlPath.toUri().toString(),
                Page.NavigateOptions()
                    .setWaitUntil(WaitUntilState.LOAD))

            page.pdf(
                Page.PdfOptions()
                    .setFormat("A4")
                    .setPrintBackground(true)
            )
        }

        nextCloudService.uploadFile(pdfBytes, user.id)

        return pdfBytes
    }
}