package com.github.bastiangessmann.gastrocore.arch

import com.github.bastiangessmann.gastrocore.common.model.entity.EstablishmentScopedEntity
import com.github.bastiangessmann.gastrocore.establishment.model.entity.Establishment
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test

class EstablishmentScopeArchitectureTest {

    private val importedClasses: JavaClasses =
        ClassFileImporter().importPackages("com.github.bastiangessmann.gastrocore")

    @Test
    fun `entities referencing Establishment must extend EstablishmentScopedEntity`() {
        val rule = ArchRuleDefinition.fields()
            .that().haveRawType(Establishment::class.java)
            .should().beDeclaredInClassesThat().areAssignableTo(EstablishmentScopedEntity::class.java)

        rule.check(importedClasses)
    }
}