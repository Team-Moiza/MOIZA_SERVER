package com.example.moiza.domain.portfolio.domain

import com.example.moiza.domain.portfolio.presentation.dto.req.*
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.*

@Entity
class Portfolio(
    user: User,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var isPublished: Boolean = false
        protected set

    @Column(nullable = false)
    var userStatus: Int = UserStatus.PORTFOLIO_PUBLISHED.level
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User = user
        protected set

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val projects: MutableList<Project> = mutableListOf()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val qualifications: MutableList<Qualification> = mutableListOf()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val awards: MutableList<Award> = mutableListOf()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    val links: MutableList<Link> = mutableListOf()

    @OneToOne(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    var introduction: Introduction? = null

    fun addProjects(projectDtos: List<ProjectDto>) {
        projectDtos.forEach { dto ->
            val project = Project(
                portfolio = this,
                title = dto.title,
                status = dto.status,
                startDate = dto.startDate!!,
                endDate = dto.endDate!!,
                description = dto.description,
                link = dto.link
            )
            projects.add(project)
        }
    }

    fun addQualifications(qualificationDtos: List<QualificationDto>) {
        qualificationDtos.forEach { dto ->
            val qualification = Qualification(
                portfolio = this,
                name = dto.name,
                score = dto.score,
                date = dto.date
            )
            qualifications.add(qualification)
        }
    }

    fun addAwards(awardDtos: List<AwardDto>) {
        awardDtos.forEach { dto ->
            val award = Award(
                portfolio = this,
                name = dto.name,
                type = dto.type,
                date = dto.date,
                description = dto.description
            )
            awards.add(award)
        }
    }

    fun addLinks(linkDtos: List<LinkDto>) {
        linkDtos.forEach { dto ->
            val link = Link(
                portfolio = this,
                url = dto.url
            )
            links.add(link)
        }
    }

    fun addIntroduction(introductionDto: IntroductionDto) {
        val introduction = Introduction(
            introduce = introductionDto.introduce,
            url = introductionDto.url,
            portfolio = this
        )
        this.introduction = introduction
    }

    fun changePublish(): Boolean {
        isPublished = !isPublished
        return isPublished
    }
}
