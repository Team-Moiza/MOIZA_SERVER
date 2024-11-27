package com.example.moiza.domain.portfolio.service

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
    private val linkRepository: LinkRepository
) {
    fun execute(portfolioId: Long, request: PortfolioRequest) {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId) ?: throw PortfolioNotFoundException

        updateProjects(portfolio, request.projects)
        updateQualifications(portfolio, request.qualifications)
        updateAwards(portfolio, request.awards)
        updateLinks(portfolio, request.links)
    }

    private fun updateProjects(portfolio: Portfolio, projectDtos: List<ProjectDto>?) {
        updateEntities(
            existing = projectRepository.findAllByPortfolio(portfolio),
            updatedDtos = projectDtos,
            create = { dto -> Project(dto.title, dto.status, dto.startDate!!, dto.endDate!!, dto.description, dto.link, portfolio) },
            update = { entity, dto -> entity.update(dto.title, dto.startDate!!, dto.endDate!!, dto.status, dto.description, dto.link) },
            delete = { projectRepository.delete(it) },
            save = { projectRepository.save(it) }
        )
    }

    private fun updateQualifications(portfolio: Portfolio, qualificationDtos: List<QualificationDto>?) {
        updateEntities(
            existing = qualificationRepository.findAllByPortfolio(portfolio),
            updatedDtos = qualificationDtos,
            create = { dto -> Qualification(dto.name, dto.score, dto.date, portfolio) },
            update = { entity, dto -> entity.update(dto.name, dto.score, dto.date) },
            delete = { qualificationRepository.delete(it) },
            save = { qualificationRepository.save(it) }
        )
    }

    private fun updateAwards(portfolio: Portfolio, awardDtos: List<AwardDto>?) {
        updateEntities(
            existing = awardRepository.findAllByPortfolio(portfolio),
            updatedDtos = awardDtos,
            create = { dto -> Award(dto.name, dto.type, dto.date, dto.description, portfolio) },
            update = { entity, dto -> entity.update(dto.name, dto.type, dto.date, dto.description) },
            delete = { awardRepository.delete(it) },
            save = { awardRepository.save(it) }
        )
    }

    private fun updateLinks(portfolio: Portfolio, linkDtos: List<LinkDto>?) {
        updateEntities(
            existing = linkRepository.findAllByPortfolio(portfolio),
            updatedDtos = linkDtos,
            create = { dto -> Link(dto.url, portfolio) },
            update = { entity, dto -> entity.update(dto.url) },
            delete = { linkRepository.delete(it) },
            save = { linkRepository.save(it) }
        )
    }

    private fun <E : Any, D : Any> updateEntities(
        // 현재 존재하는 섹션들의 기존 목록
        existing: List<E>,
        // request에서 받는 새로운 섹션 리스트
        updatedDtos: List<D>?,
        // 새로운 섹션 엔티티 생성 함수 (기존 리스트에서 추가로 들어온 값)
        create: (D) -> E,
        // 기존 섹션 엔티티 업데이트하는 함수 (기존 리스트에서 수정된 값)
        update: (E, D) -> Unit,
        // 기존 섹션 삭제하는 람다 함수 (기존 리스트에서 삭제된 값)
        delete: (E) -> Unit,
        // 저장
        save: (E) -> Unit
    ) {
        /*
        dto 리스트를 순회하며 각 dto에
        1. if 인덱스가 기존 리스트 크기보다 작으면 엔티티 업데이트
        2. else 그렇지 않다면 엔티티 생성 후 저장
        */
        updatedDtos?.forEachIndexed { index, dto ->
            if (index < existing.size) {
                update(existing[index], dto)
            } else {
                save(create(dto))
            }
        }

        // 만약 dto 리스트 크기가 기존 엔티티 목록 갯수보다 작으면 기존 엔티티 삭제
        if (updatedDtos != null && updatedDtos.size < existing.size) {
            existing.subList(updatedDtos.size, existing.size).forEach(delete)
        }
    }
}
