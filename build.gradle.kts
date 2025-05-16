plugins {
    // do not apply Spring Boot at root
    kotlin("jvm") version "1.9.25" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("org.springframework.boot") version "3.4.5" apply false
}

allprojects {
    group = "com.example.gastrocore"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}