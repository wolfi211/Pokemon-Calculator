# PokéCounter

A Pokémon counter dashboard built with Vue 3, Vite, and a Spring Boot backend. Find "Perfect Walls" and "Strong Alternatives" against specific Pokémon threats, with tiered results, stat sorting, and Bulbapedia links.

## Features

- Smart tiering: groups counters into Perfect Walls and Strong Alternatives
- Dynamic sorting within tiers by base stats
- Collapsible tier sections
- Pokémon and move autocomplete backed by the Spring API
- Counter calculation runs server-side

## Setup

### 1. Clone and install

```bash
git clone https://github.com/wolfi211/Pokemon-Calculator.git
cd Pokemon-Calculator/poke-counter-frontend
npm install
```

### 2. Backend and database

The frontend expects the Spring Boot API in [`poke-counter-backend`](poke-counter-backend/). See [`poke-counter-backend/README.md`](poke-counter-backend/README.md) for Postgres setup.

Populate data from PokéAPI:

```bash
curl -X POST http://localhost:8080/api/v1/sync
```

### 3. Environment variables

Copy `.env.example` to `.env` in `poke-counter-frontend/`:

```bash
# Leave empty for local dev (Vite proxies /api to localhost:8080)
VITE_API_BASE_URL=

# Production build example:
# VITE_API_BASE_URL=https://api.example.com
```

## Development

Start the backend (port 8080), then the frontend:

```bash
# Terminal 1 — backend
cd poke-counter-backend
./gradlew :poke-counter:bootRun

# Terminal 2 — frontend
cd poke-counter-frontend
npm run dev
```

Open http://localhost:5173. API requests go to `/api/v1/...` and are proxied to the backend in dev.

## Build

```bash
cd poke-counter-frontend
npm run build
```

Set `VITE_API_BASE_URL` to your public API origin before building for production.

## Deployment note

Root `Dockerfile` and `docker-compose.yml` still reflect the old Supabase-only setup and need updating for a full-stack deployment (static frontend + API reverse proxy).

## Contributing

Open an issue or pull request on GitHub.
