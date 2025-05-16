package com.github.bastiangessmann.gastrocore.auth.service

import com.github.bastiangessmann.gastrocore.auth.model.SessionRequest
import com.github.bastiangessmann.gastrocore.auth.model.entity.VisitorSession
import com.github.bastiangessmann.gastrocore.auth.repository.VisitorSessionRepository
import com.github.bastiangessmann.gastrocore.common.config.JwtProvider
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.UUID

@Service
class VisitorSessionService(
    private val sessionRepo: VisitorSessionRepository,
    private val jwtProvider: JwtProvider,
) {

    fun createSession(sessionRequest: SessionRequest): String {
        val visitorSession = sessionRepo.save(VisitorSession(tableId = sessionRequest.tableId))
        return createToken(session = visitorSession, establishmentId = sessionRequest.establishmentId)
    }

    private fun createToken(session: VisitorSession, establishmentId: UUID): String {
        return jwtProvider.createToken(
            subject = session.id.toString(),
            additionalClaims = mapOf(
                "tableId" to session.tableId,
                "establishmentId" to establishmentId,
                "roles" to listOf("VISITOR_ANON")
            ),
            expiresIn = Duration.ofHours(2)
        )
    }


}