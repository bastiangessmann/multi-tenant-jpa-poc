package com.github.bastiangessmann.gastrocore.common.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.TemporalAmount
import java.util.*

@Component
class JwtProvider(
    private val props: JwtProperties
) {

    /**
     * Build a signed JWT.
     *
     * @param subject        the token subject (e.g. session or user ID)
     * @param additionalClaims any extra claims to embed (e.g. roles, tableId)
     * @param expiresIn      how long the token is valid for
     * @return               a compact JWT string
     */
    fun createToken(
        subject: String,
        additionalClaims: Map<String, Any>,
        expiresIn: TemporalAmount
    ): String {
        val now = Instant.now()
        return Jwts.builder()
            .setIssuer(props.issuer)
            .setSubject(subject)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(expiresIn)))
            .addClaims(additionalClaims)
            .signWith(Keys.hmacShaKeyFor(props.secret.toByteArray()))
            .compact()
    }

    /**
     * Parse and validate a JWT, returning its claims.
     *
     * @param token the JWT string from the client
     * @throws io.jsonwebtoken.JwtException if invalid or expired
     */
    fun parseClaims(token: String): Claims = Jwts.parserBuilder()
        .setSigningKey(props.secret.toByteArray())
        .build()
        .parse(token)
        .body as Claims
}