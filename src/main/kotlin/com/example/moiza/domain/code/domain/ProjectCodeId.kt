package com.example.moiza.domain.code.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.EqualsAndHashCode
import java.io.Serializable

@Embeddable
@EqualsAndHashCode
class ProjectCodeId(
    projectId: Long,
    codeId: Long
): Serializable {
    @Column(name = "project_id")
    var projectId: Long = projectId
        protected set

    @Column(name = "code_id")
    var codeId: Long = codeId
        protected set
}
