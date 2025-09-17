# Shop Now Backend (Clean Architecture MVP)

E-commerce backend (filosofía Shopify: lanzar rápido, iterar y escalar) con:

- Java 21
- Spring Boot 3
- Gradle (Kotlin DSL)
- Postgres + Flyway
- Clean Architecture (domain / application / infrastructure / interfaces web)

## Esquema Inicial

Migración inicial (`V1__initial_schema.sql`) basada exactamente en el esquema proporcionado (nombres entrecomillados y tipos decimal). Se recomienda más adelante normalizar a snake_case + UUID nativo.

## Capas

```
src/main/java/com/shopnow
	├─ domain/            # Entidades de dominio + puertos (repos)
	├─ application/       # Casos de uso (servicios y DTO commands)
	├─ infrastructure/    # Adaptadores (persistence, web, config)
	└─ ShopNowApplication.java
```

## Endpoints MVP (Products)

| Método | Path               | Descripción      |
| ------ | ------------------ | ---------------- |
| POST   | /api/products      | Crea un producto |
| GET    | /api/products/{id} | Obtiene producto |
| GET    | /api/products      | Lista productos  |

## Variables de entorno

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=shopnow
DB_USER=shopnow
DB_PASSWORD=shopnow
PORT=8080
```

## Ejecutar

```bash
gradle wrapper --gradle-version 8.10
./gradlew bootRun
```

## Próximos pasos sugeridos

- Implementar Users / Orders / Cart / Wishlist / Inventory
- Añadir autenticación (JWT / API Key)
- Migrar IDs a tipo UUID nativo (requiere nueva migración)
- Añadir índices y constraints únicos (ej. Users.username, Users.email)
- Tests con Testcontainers
