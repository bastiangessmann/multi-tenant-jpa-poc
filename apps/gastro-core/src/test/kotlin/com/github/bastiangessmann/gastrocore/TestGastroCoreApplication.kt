package com.github.bastiangessmann.gastrocore

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<GastroCoreApplication>().with(TestcontainersConfiguration::class).run(*args)
}
