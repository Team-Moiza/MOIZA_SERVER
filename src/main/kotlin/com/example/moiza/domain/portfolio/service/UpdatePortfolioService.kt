package com.example.moiza.domain.portfolio.service

import com.example.moiza.domain.code.domain.PortfolioCode
import com.example.moiza.domain.code.domain.repository.CodeRepository
import com.example.moiza.domain.code.domain.repository.PortfolioCodeRepository
import com.example.moiza.domain.portfolio.domain.*
import com.example.moiza.domain.portfolio.domain.repository.*
import com.example.moiza.domain.portfolio.exception.PortfolioNotFoundException
import com.example.moiza.domain.portfolio.presentation.dto.req.*
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class UpdatePortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val projectRepository: ProjectRepository,
    private val qualificationRepository: QualificationRepository,
    private val awardRepository: AwardRepository,
    private val linkRepository: LinkRepository,
    private val portfolioCodeRepository: PortfolioCodeRepository,
    private val codeRepository: CodeRepository
) {
    fun execute(portfolioId: Long, request: UpdatePortfolioRequest) {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException

        updateProjects(portfolio, request.projects)
        updateQualifications(portfolio, request.qualifications)
        updateAwards(portfolio, request.awards)
        updateLinks(portfolio, request.links)
        updateIntroduction(portfolio, request.introduction)
        updateCodes(portfolio, request.codes)
    }

    private fun updateProjects(portfolio: Portfolio, projectDtos: List<ProjectDto>?) {
        updateEntities(
            existing = projectRepository.findAllByPortfolio(portfolio),
            updated = projectDtos,
            match = { entity, dto -> entity.id == dto.id },
            create = { dto -> Project(dto.title, dto.status, dto.startDate!!, dto.endDate, dto.description, dto.link, portfolio) },
            update = { entity, dto -> entity.update(dto.title, dto.startDate!!, dto.endDate, dto.status, dto.description, dto.link) },
            delete = { projectRepository.delete(it) },
            save = { projectRepository.save(it) }
        )
    }

    private fun updateQualifications(portfolio: Portfolio, qualificationDtos: List<QualificationDto>?) {
        updateEntities(
            existing = qualificationRepository.findAllByPortfolio(portfolio),
            updated = qualificationDtos,
            match = { entity, dto -> entity.id == dto.id },
            create = { dto -> Qualification(dto.name, dto.score, dto.date, portfolio) },
            update = { entity, dto -> entity.update(dto.name, dto.score, dto.date) },
            delete = { qualificationRepository.delete(it) },
            save = { qualificationRepository.save(it) }
        )
    }

    private fun updateAwards(portfolio: Portfolio, awardDtos: List<AwardDto>?) {
        updateEntities(
            existing = awardRepository.findAllByPortfolio(portfolio),
            updated = awardDtos,
            match = { entity, dto -> entity.id == dto.id },
            create = { dto -> Award(dto.name, dto.type, dto.date, dto.description, portfolio) },
            update = { entity, dto -> entity.update(dto.name, dto.type, dto.date, dto.description) },
            delete = { awardRepository.delete(it) },
            save = { awardRepository.save(it) }
        )
    }

    private fun updateLinks(portfolio: Portfolio, linkDtos: List<LinkDto>?) {
        updateEntities(
            existing = linkRepository.findAllByPortfolio(portfolio),
            updated = linkDtos,
            match = { entity, dto -> entity.id == dto.id },
            create = { dto -> Link(dto.url, portfolio) },
            update = { entity, dto -> entity.update(dto.url) },
            delete = { linkRepository.delete(it) },
            save = { linkRepository.save(it) }
        )
    }

    private fun updateIntroduction(portfolio: Portfolio, introductionDto: IntroductionDto?) {
        updateEntities(
            existing = listOfNotNull(portfolio.introduction),
            updated = introductionDto?.let { listOf(it) },
            match = { _, _ -> true },
            create = { dto -> Introduction(dto.introduce, dto.url, portfolio) },
            update = { entity, dto -> entity.update(dto.introduce, dto.url) },
            delete = { portfolio.introduction = null },
            save = { portfolio.introduction = it }
        )
    }

    private fun updateCodes(portfolio: Portfolio, codes: List<CodeDto>?) {
        updateEntities(
            existing = portfolioCodeRepository.findAllByPortfolio(portfolio),
            updated = codes?.mapNotNull { codeRepository.findByIdOrNull(it.id) },
            match = { entity, code -> entity.code.id == code.id },
            create = { code -> PortfolioCode(portfolio, code) },
            update = { _, _ -> },
            delete = { portfolioCodeRepository.delete(it) },
            save = { portfolioCodeRepository.save(it) }
        )
    }

    private fun <E : Any, D : Any> updateEntities(
        existing: List<E>,
        updated: List<D>?,
        match: (E, D) -> Boolean,
        create: (D) -> E,
        update: (E, D) -> Unit,
        delete: (E) -> Unit,
        save: (E) -> Unit
    ) {
        val writing = mutableSetOf<E>()

        updated?.forEach { dto ->
            val entity = existing.find { match(it, dto) }
            if (entity != null) {
                update(entity, dto)
                writing.add(entity)
            } else {
                save(create(dto))
            }
        }

        existing.filterNot { writing.contains(it) }.forEach(delete)
    }
}
