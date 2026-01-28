# АвтоХаб MVP

## Запуск через Docker

### Требования
- Docker Desktop или Docker Engine
- Docker Compose (обычно уже встроен в Docker Desktop)

### Шаги запуска
1. В корне проекта выполните:
   ```bash
   docker compose up --build
   ```
2. Дождитесь, пока поднимутся все сервисы.

### Адреса приложений
- Backend API: `http://localhost:8080`
- Клиент: `http://localhost:3000`
- Мастер: `http://localhost:3001`
- Админ: `http://localhost:3002`
- Postgres: `localhost:5432` (db `autohub`, user `autohub`, password `autohub`)

### Остановка
```bash
docker compose down
```

### Полезно знать
- Данные Postgres сохраняются в volume `db_data`.
- Для Docker фронты ходят на `/api` (прокси на backend).
- Если запускаете без Docker, задайте:
  - `NUXT_PUBLIC_API_BASE=http://localhost:8080`
  - `NUXT_API_PROXY_TARGET=http://localhost:8080`

## Dev запуск контейнеров (быстрая проверка)

### Все сервисы в dev-режиме
```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build
```

### Запуск отдельных сервисов в dev-режиме
```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build backend
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build client
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build master
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build admin
```

### Запуск отдельных сервисов в prod-режиме
```bash
docker compose up --build backend
docker compose up --build client
docker compose up --build master
docker compose up --build admin
```
