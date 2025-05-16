package com.github.bastiangessmann.gastrocore.menu.model.entity

import com.github.bastiangessmann.gastrocore.common.model.entity.EstablishmentScopedEntity
import com.github.bastiangessmann.gastrocore.establishment.model.entity.Establishment
import jakarta.persistence.*

@Table(name = "menu_items")
@Entity
class MenuItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "establishment_external_id",
        referencedColumnName = "external_id",
        nullable = false,
        updatable = false
    )
    val establishment: Establishment
) : EstablishmentScopedEntity()