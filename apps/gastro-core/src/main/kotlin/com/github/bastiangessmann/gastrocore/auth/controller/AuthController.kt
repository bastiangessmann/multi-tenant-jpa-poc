package com.github.bastiangessmann.gastrocore.auth.controller

import com.github.bastiangessmann.gastrocore.auth.model.SessionRequest
import com.github.bastiangessmann.gastrocore.auth.model.TokenResponse
import com.github.bastiangessmann.gastrocore.auth.service.VisitorSessionService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/auth")
class AuthController(private val sessionService: VisitorSessionService) {

    /**
     * Scan-in endpoint for anonymous visitor.
     * Returns a JWT that the client will use for all subsequent calls.
     */
    @PostMapping("/visitor-session")
    fun createVisitorSession(
        @RequestBody sessionRequest: SessionRequest,
    ): ResponseEntity<TokenResponse> {
        val jwt = sessionService.createSession(sessionRequest = sessionRequest)
        return ResponseEntity.ok(TokenResponse(jwt))
    }
}