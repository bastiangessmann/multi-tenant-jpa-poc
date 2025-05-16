package com.github.bastiangessmann.gastrocore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing
class GastroCoreApplication

fun main(args: Array<String>) {
    runApplication<GastroCoreApplication>(*args)
}
