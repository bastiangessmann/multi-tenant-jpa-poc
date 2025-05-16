package com.github.bastiangessmann.gastrocore.auth.repository

import com.github.bastiangessmann.gastrocore.auth.model.entity.VisitorSession
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VisitorSessionRepository : JpaRepository<VisitorSession, UUID>