package com.example.moiza.global.utils.thymeleaf

import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context


@Component
class ProcessTemplateService(
    private val templateEngine: TemplateEngine,
) {
    fun execute(template: String, data: Map<String, Any>): String {
        val context = Context().apply {
            setVariables(data)
        }

        return templateEngine.process(template, context)
    }
}