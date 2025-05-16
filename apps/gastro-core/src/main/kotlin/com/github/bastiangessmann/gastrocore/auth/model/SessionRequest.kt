package com.github.bastiangessmann.gastrocore.auth.model

import java.util.*

data class SessionRequest(
    val tableId: UUID,

    val establishmentId: UUID,
)