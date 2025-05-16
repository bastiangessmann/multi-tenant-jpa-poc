# Multi-Tenant JPA Filter POC

This mono repo is a **proof of concept** for implementing a **multi-tenant data access layer** in a gastronomy setting using **Spring Boot, Spring Security, and JPA/Hibernate**.

---

## Overview

The goal is to ensure that all `JpaRepository` operations are automatically scoped to the **current tenant (establishment)** for example, a **restaurant** in a multi-establishment system.

A Hibernate filter is applied dynamically at runtime to enforce tenant isolation, so each request only sees data belonging to its establishment.

---

## Domain Example

- **Establishment** → represents a restaurant
- **Table** → represents a table within a restaurant
- **Menu** → represents items belonging to that establishment

Each authenticated session (via `VisitorSessionPrincipal`) is associated with one establishment.  
All repository queries automatically filter by that tenant.

---

## Technical

- **Aspect (`TenantFilterAspect`)** wraps all `JpaRepository` calls
- **Hibernate filter** enforces tenant isolation at the database level
- **Spring Security** provides tenant identity via authentication context
- **Dockerized Postgres** database for local testing
- **`.http` files** for testing API endpoints

---

## Run Locally

```bash
# Start local database
docker compose -f common/files/docker/postgres/docker-compose.yml up -d

# Run the application
./gradlew bootRun
```
