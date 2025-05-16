package com.github.bastiangessmann.gastrocore.auth.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*

@Entity(name = "visitor_sessions")
@EntityListeners(AuditingEntityListener::class)
class VisitorSession(
    @Id
    val id: UUID = UUID.randomUUID(),
    val tableId: UUID,
) {
    @CreatedDate
    var createdAt: Instant? = null
}