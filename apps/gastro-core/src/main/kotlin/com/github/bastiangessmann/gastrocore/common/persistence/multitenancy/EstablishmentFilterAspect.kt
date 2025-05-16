package com.github.bastiangessmann.gastrocore.common.persistence.multitenancy

import com.github.bastiangessmann.gastrocore.auth.model.VisitorSessionPrincipal
import jakarta.persistence.EntityManager
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.hibernate.Session
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Aspect
@Component
class EstablishmentFilterAspect(
    private val em: EntityManager
) {

    /**
     * Intercepts all method executions on {@link org.springframework.data.jpa.repository.JpaRepository}
     * implementations to apply tenant-based data filtering automatically.
     *
     * <p>This advice enables a Hibernate filter named {@code establishmentFilter} for the duration of the
     * repository method call, ensuring that data access is restricted to the tenant (establishment)
     * associated with the currently authenticated {@link VisitorSessionPrincipal}.</p>
     *
     * <p>After the repository method completes—whether successfully or by throwing an exception—
     * the filter is disabled to avoid leaking tenant context across operations.</p>
     *
     * @param joinPoint the {@link ProceedingJoinPoint} representing the intercepted repository method call.
     *                  Used to proceed with the method execution inside the advice.
     * @return the result of the intercepted repository method call.
     *
     * @throws Throwable if the underlying repository method throws any exception.
     *
     * @see Around
     * @see org.springframework.data.jpa.repository.JpaRepository
     * @see VisitorSessionPrincipal
     */
    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
    fun wrapRepoCalls(joinPoint: ProceedingJoinPoint): Any? {
        val auth = SecurityContextHolder.getContext().authentication
        val session = em.unwrap(Session::class.java)

        if (auth?.principal is VisitorSessionPrincipal) {
            val tenantId = (auth.principal as VisitorSessionPrincipal).establishmentId
            session.enableFilter("establishmentFilter")
                .setParameter("establishmentExternalId", tenantId)
        }

        return try {
            joinPoint.proceed()
        } finally {
            session.disableFilter("establishmentFilter")
        }
    }
}