package com.github.bastiangessmann.gastrocore.common.model.entity

import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import java.util.*

@FilterDef(
    name = "establishmentFilter",
    parameters = [ParamDef(name = "establishmentExternalId", type = UUID::class)]
)
@Filter(
    name = "establishmentFilter",
    condition = "establishment_external_id = :establishmentExternalId"
)
@MappedSuperclass
abstract class EstablishmentScopedEntity