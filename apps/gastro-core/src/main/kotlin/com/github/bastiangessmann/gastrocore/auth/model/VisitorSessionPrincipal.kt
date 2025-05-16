package com.github.bastiangessmann.gastrocore.auth.model

import java.util.*

data class VisitorSessionPrincipal(
    val tableId: UUID,
    val sessionId: String,
    val establishmentId: UUID,
)