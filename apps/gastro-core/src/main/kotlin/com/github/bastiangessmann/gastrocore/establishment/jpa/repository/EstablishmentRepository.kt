package com.github.bastiangessmann.gastrocore.establishment.jpa.repository

import com.github.bastiangessmann.gastrocore.establishment.model.entity.Establishment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EstablishmentRepository : JpaRepository<Establishment, Long> {
    fun findEstablishmentByExternalId(externalId: UUID): Establishment?
}