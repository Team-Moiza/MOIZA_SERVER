package com.example.moiza.domain.portfolio.domain

import com.example.moiza.domain.BaseTimeEntity
import com.example.moiza.domain.code.domain.Code
import com.example.moiza.domain.code.domain.PortfolioCode
import com.example.moiza.domain.like.domain.Like
import com.example.moiza.domain.portfolio.presentation.dto.req.*
import com.example.moiza.domain.portfolio.domain.type.UserStatus
import com.example.moiza.domain.user.domain.User
import jakarta.persistence.*

@Entity
class Portfolio(
    user: User,
    title: String,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var isPublished: Boolean = false
        protected set

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var userStatus: Int = UserStatus.PORTFOLIO_PUBLISHED.level
        protected set

    @Column(nullable = false)
    var likeCnt: Long = 0L

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

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val _portfolioCodes: MutableList<PortfolioCode> = mutableListOf()
    val codes: List<PortfolioCode> get() = _portfolioCodes.toList()

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val likes: MutableList<Like> = mutableListOf()

    fun addCode(code: Code) {
        val portfolioCode = PortfolioCode(this, code)
        _portfolioCodes.add(portfolioCode)
    }

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

    fun changePublish(status: Boolean) {
        this.isPublished = status
    }

    fun like() {
        likeCnt++
    }

    fun unLike() {
        likeCnt--
    }
}
