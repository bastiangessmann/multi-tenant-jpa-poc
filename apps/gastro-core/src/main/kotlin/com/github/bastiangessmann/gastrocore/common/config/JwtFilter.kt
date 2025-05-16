package com.github.bastiangessmann.gastrocore.common.config

import com.github.bastiangessmann.gastrocore.auth.model.VisitorSessionPrincipal
import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.util.UUID

/**
 * A simple servlet filter that:
 * 1. Extracts the Bearer token from the Authorization header
 * 2. Uses JwtTokenProvider to parse & validate it
 * 3. Builds a Spring Authentication with roles from the token
 */
class JwtFilter(private val jwtProvider: JwtProvider) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpReq = request as HttpServletRequest
        val header = httpReq.getHeader("Authorization")

        if (!header.isNullOrBlank() && header.startsWith("Bearer ")) {
            try {
                val token = header.removePrefix("Bearer ").trim()
                val claims: Claims = jwtProvider.parseClaims(token)
                val sessionId = claims.subject

                val roles = claims.get("roles", List::class.java)
                    .map { SimpleGrantedAuthority("ROLE_$it") }

                val principal = VisitorSessionPrincipal(
                    tableId = UUID.fromString(claims.get("tableId", String::class.java)),
                    sessionId = sessionId,
                    establishmentId = UUID.fromString(claims.get("establishmentId", String::class.java))
                )

                // Build an Authentication holding our sessionId as principal
                val auth = UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    roles,
                )
                    .apply {
                        details = claims
                    }
                SecurityContextHolder.getContext().authentication = auth
            } catch (_: Exception) {
                // invalid token → clear context and proceed (will be rejected downstream)
                SecurityContextHolder.clearContext()
            }
        }

        chain.doFilter(request, response)
    }
}