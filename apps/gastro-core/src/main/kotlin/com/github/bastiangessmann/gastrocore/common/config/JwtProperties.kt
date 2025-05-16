package com.github.bastiangessmann.gastrocore.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Binds JWT configuration from application.yml:
 *
 * jwt:
 *   secret: very-secure-random-string
 *   issuer: gastro-core
 */
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    /** Secret key for signing and verifying JWTs. */
    val secret: String,
    /** JWT “iss” claim: identifies your application or realm. */
    val issuer: String,
)