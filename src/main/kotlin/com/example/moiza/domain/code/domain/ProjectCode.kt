package com.example.moiza.domain.code.domain

import com.example.moiza.domain.portfolio.domain.Project
import jakarta.persistence.*

@Entity
class ProjectCode(
    project: Project,
    code: Code
) {
    @EmbeddedId
    val id: ProjectCodeId = ProjectCodeId(project.id, code.id)

    @MapsId("projectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project = project
        protected set

    @MapsId("codeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    var code: Code = code
        protected set
}
