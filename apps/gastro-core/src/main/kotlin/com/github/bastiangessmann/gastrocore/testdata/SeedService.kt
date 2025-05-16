package com.github.bastiangessmann.gastrocore.testdata

import com.github.bastiangessmann.gastrocore.establishment.model.entity.Establishment
import com.github.bastiangessmann.gastrocore.establishment.jpa.repository.EstablishmentRepository
import com.github.bastiangessmann.gastrocore.menu.model.entity.MenuItem
import com.github.bastiangessmann.gastrocore.menu.repository.MenuItemRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class SeedService(
    private val menuItemRepository: MenuItemRepository,
    private val establishmentRepository: EstablishmentRepository,
    @PersistenceContext
    private val em: EntityManager,

    ) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        if (establishmentRepository.count() < 1) {
            establishmentRepository.save(
                Establishment(
                    externalId = UUID.fromString("98dca1a9-c457-4abd-89c5-879bc6c8b06d")
                )
            )
            establishmentRepository.save(
                Establishment(
                    externalId = UUID.fromString("15dca1a9-c457-4abd-89c5-879bc6c8b06d")
                )
            )
        }

        if (menuItemRepository.count() < 1) {
            menuItemRepository.save(
                MenuItem(
                    name = "Burger",
                    establishment = requireNotNull(
                        establishmentRepository.findEstablishmentByExternalId(externalId = UUID.fromString("98dca1a9-c457-4abd-89c5-879bc6c8b06d"))
                    )
                )
            )

            menuItemRepository.save(
                MenuItem(
                    name = "Fries",
                    establishment = requireNotNull(
                        establishmentRepository.findEstablishmentByExternalId(externalId = UUID.fromString("15dca1a9-c457-4abd-89c5-879bc6c8b06d"))
                    )
                )
            )
        }

    }

}