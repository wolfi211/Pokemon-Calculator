# PokéCounter Mobile — Compose Multiplatform Roadmap

This folder will hold the **Compose Multiplatform (CMP)** client for PokéCounter — a shared Kotlin UI for **iOS and Android** that talks to the existing Spring Boot API in `[poke-counter-backend](../poke-counter-backend/)`.

The web app in `[poke-counter-frontend](../poke-counter-frontend/)` is the reference for features and API contracts. The mobile app reuses the same endpoints and user journey with a layout tuned for phones.

---

## Strategy


| Decision                     | Choice                                                            |
| ---------------------------- | ----------------------------------------------------------------- |
| UI framework                 | Compose Multiplatform (shared UI in `commonMain`)                 |
| Primary platform (phase 1)   | **iOS** — develop and test on a physical iPhone                   |
| Secondary platform (phase 2) | **Android** — same `composeApp` module, thin `androidApp` shell   |
| Backend                      | Existing Spring API at `/api/v1/...` (no mobile-specific backend) |
| Dev machine                  | Mac with Xcode (iOS builds) + Android Studio / IntelliJ           |


---

## Target project structure

When the CMP project is scaffolded, aim for this layout inside `poke-counter-mobile/`:

```
poke-counter-mobile/
├── composeApp/                 # Shared UI, ViewModels, networking
│   ├── src/
│   │   ├── commonMain/kotlin/  # Screens, theme, API, repositories
│   │   ├── iosMain/kotlin/     # iOS entry, platform helpers
│   │   └── androidMain/kotlin/ # Android entry, manifest helpers
│   └── build.gradle.kts
├── iosApp/                     # Xcode project (thin shell)
│   └── iosApp.xcodeproj
├── androidApp/                 # Android application module (phase 2)
├── gradle/
├── settings.gradle.kts
└── ROADMAP.md                  # This file
```

**Code reuse target:** ~90–95% in `commonMain`. Platform folders only for signing, networking quirks, URL opening, haptics, etc.

---

## Architecture (mirrors the Vue frontend)

```
UI (Composables)              ←  Vue components (.vue)
    ↕
ViewModel (StateFlow)         ←  Pinia / component script
    ↕
Repository                    ←  PokemonCalculatorService, *Api.ts
    ↕
Ktor Client (commonMain)      ←  api/client.ts
    ↕
Spring Boot API               ←  poke-counter-backend (unchanged)
```

### Suggested `commonMain` packages

```
data/
  api/          PokeApi, MoveApi, CounterApi
  dto/          CounterFindRequest, CounterResultDto, PokemonSummaryDto, ...
  repository/   CounterRepository, PokemonRepository
ui/
  counter/
    CounterViewModel.kt
    EnemySearchScreen.kt
    MovePickerScreen.kt
    ResultsScreen.kt
  theme/        Type colors (align with frontend TypeColors)
App.kt          NavHost root
```

### `expect` / `actual` (add only when needed)

- Base URL per build type (debug vs release)
- `openUrl(url)` — Safari on iOS, Chrome/default on Android
- Optional haptics on primary actions
- Platform-specific logging

Keep business logic and screens in `commonMain` as long as possible.

---

## API surface

Same contract as the Vue app. Swagger/OpenAPI lives in the backend.


| Method | Endpoint                                               | Purpose                                          |
| ------ | ------------------------------------------------------ | ------------------------------------------------ |
| `GET`  | `/api/v1/pokemon/minified-search?query=&versionGroup=` | Enemy Pokémon autocomplete                       |
| `GET`  | `/api/v1/moves/minified-search?query=&pokemonId=`      | Move autocomplete (filtered by selected Pokémon) |
| `POST` | `/api/v1/counters/find`                                | Counter calculation                              |


### Request / response (reference)

**Counter find request:**

```json
{
  "enemyPokemonId": 6,
  "enemyMoveIds": [123, 456]
}
```

**Counter find response:** list of results with `pokemon`, `tier`, `hasStab` (see `poke-counter-frontend/src/types/api/counterResult.ts`).

### Local development URLs


| Environment              | Base URL                                                 |
| ------------------------ | -------------------------------------------------------- |
| iOS Simulator            | `http://localhost:8080` (simulator shares Mac localhost) |
| Physical iPhone          | `http://<your-mac-lan-ip>:8080` (same Wi‑Fi as Mac)      |
| Android emulator (later) | `http://10.0.2.2:8080`                                   |


Start the backend:

```bash
cd poke-counter-backend
./gradlew :poke-counter:bootRun
```

Populate data if needed:

```bash
curl -X POST http://localhost:8080/api/v1/sync
```

**iOS HTTP note:** For non-HTTPS local dev, add an **App Transport Security** exception in `iosApp` `Info.plist` for the dev host.

**Android HTTP note (phase 2):** `INTERNET` permission + network security config for cleartext to emulator host.

---

## Tooling checklist

### Mac (required for iOS)

- [x] **Xcode** 15+ (current stable)
- [x] **Android Studio** (latest) with **Kotlin Multiplatform** plugin
- [x] JDK 17+
- [x] Run **Tools → Kotlin Multiplatform → Doctor** and fix any issues

### Apple Developer (for physical iPhone)

- [x] Apple ID added in Xcode
- [x] Unique **Bundle ID** (e.g. `hu.danielwolf.pokecounter`)
- [x] **Developer Mode** enabled on iPhone (Settings → Privacy & Security)
- [x] Device trusted in Xcode (Window → Devices and Simulators)

### Libraries to add in `composeApp` (as you build)


| Library               | Purpose                  |
| --------------------- | ------------------------ |
| Navigation Compose    | Screen flow              |
| ViewModel + Lifecycle | UI state across rotation |
| Ktor Client           | HTTP                     |
| kotlinx.serialization | JSON DTOs                |
| Coil (optional)       | Pokémon sprites          |


---

## Project creation (when you start)

1. **File → New → Project → Kotlin Multiplatform Application** (Compose Multiplatform UI).
2. Enable **iOS** and **Android** (keep Android module even while focusing on iOS).
3. Package name: e.g. `hu.danielwolf.pokecounter`.
4. Run default template on **iOS Simulator**, then on **physical iPhone**.
5. Configure signing in Xcode: `iosApp` target → Signing & Capabilities → Team + Bundle ID.
6. First iOS build can take 5–10 minutes (Kotlin/Native); later builds are incremental.

Official guide: [Create your Compose Multiplatform app](https://kotlinlang.org/docs/multiplatform/compose-multiplatform-create-first-app.html)

---

## Learning milestones

### Milestone 0 — Hello iPhone

**Goal:** Default CMP template running on a physical device.

**Learn:** Xcode signing, Developer Mode, `iosApp` run configuration, Kotlin/Native compile times.

---

### Milestone 1 — Hello API

**Goal:** `GET /api/v1/pokemon/minified-search?query=char` → results in a `LazyColumn`.

**Learn:** Ktor in `commonMain`, coroutines, `LaunchedEffect`, loading / empty / error states, dev base URL for device vs simulator.

---

### Milestone 2 — Navigation

**Goal:** Three-screen flow with Navigation Compose:

```
Pick enemy  →  Pick moves (1–4)  →  Tiered results
```

**Learn:** NavHost, route arguments, back stack (iOS swipe-back), clearing moves when enemy changes (same rule as web `SearchBar.vue`).

**UX:** Vertical, one decision per step — do **not** copy the desktop side-by-side layout.

---

### Milestone 3 — Feature parity

**Goal:** Full counter flow: POST `/counters/find`, show Perfect Walls and Strong Alternatives.

**Learn:** `CounterViewModel` + `StateFlow`, expandable tier sections, type chips, STAB indicator, empty results vs errors.

---

### Milestone 4 — iOS polish

**Goal:** App feels at home on iPhone.

**Learn:**

- Safe areas (`WindowInsets`, notch / Dynamic Island)
- Primary CTA at bottom (thumb zone)
- `expect/actual` for `openUrl` (Bulbapedia links)
- Optional haptics on “Find Counters”
- Dark theme aligned with web (`bg-neutral-800` aesthetic)

---

### Milestone 5 — Android

**Goal:** Run `androidApp` on emulator or device without rewriting screens.

**Learn:**

- `AndroidManifest`: `INTERNET`, cleartext config for `10.0.2.2`
- Emulator localhost mapping
- Material back gesture vs iOS swipe-back (Navigation Compose handles both)

---

## Mobile UX principles (PokéCounter-specific)

The core job on a phone: **quickly answer “what walls this?”** during battle or team preview.

1. **Few taps** — open app → enemy → moves → results.
2. **Progressive disclosure** — one screen per major step; avoid four tiny pickers on one view.
3. **Search pattern** — debounced autocomplete (~300 ms); full-screen or large sheet for search.
4. **Loading feedback** — skeleton or spinner during search and counter POST (server-side calculation can be slow).
5. **Trust** — on results, show enemy name, types, and selected moves so the user can verify input.
6. **Errors** — distinguish network failure, API 400, and legitimately empty counter lists.
7. **Defer offline cache** to a later milestone; v1 can be online-only.

### Results layout

- Group by tier (Perfect Walls, Strong Alternatives) — collapsible sections or tabs.
- Row: name, type chips, tier badge, STAB.
- Tap row → detail sheet with stats and external link.

---

## Web → CMP concept map


| Vue / TypeScript   | Compose Multiplatform                           |
| ------------------ | ----------------------------------------------- |
| `ref` / `reactive` | `mutableStateOf` / `StateFlow`                  |
| `watch`            | `LaunchedEffect`, `collectAsStateWithLifecycle` |
| `emit('results')`  | ViewModel updates state; UI collects            |
| `fetch` / `apiGet` | Ktor suspend functions                          |
| Components         | `@Composable` functions                         |
| `vue-router`       | Navigation Compose                              |
| Tailwind           | `Modifier`, Material 3                          |


---

## Platform comparison (what you learn where)


| Topic          | iOS (phase 1)                            | Android (phase 2)            |
| -------------- | ---------------------------------------- | ---------------------------- |
| Code signing   | Apple Developer, Team ID, profiles       | Debug keystore, Play signing |
| Dev networking | LAN IP on device; localhost on Simulator | `10.0.2.2` on emulator       |
| HTTP cleartext | ATS exception in Info.plist              | `networkSecurityConfig`      |
| Store          | App Store Connect, TestFlight            | Play Console                 |
| Permissions    | Info.plist usage descriptions            | AndroidManifest              |


CMP uses **Material 3** on both platforms for v1 — acceptable for shipping. iOS-specific spacing tweaks can live in theme code later without splitting screens.

---

## Recommended resources

- [Compose Multiplatform — first app](https://kotlinlang.org/docs/multiplatform/compose-multiplatform-create-first-app.html)
- [KMP project structure](https://kotlinlang.org/docs/multiplatform-discover-project.html)
- [Material 3 for Compose](https://m3.material.io/develop/android/jetpack-compose)
- [Android app architecture (ViewModel + Repository)](https://developer.android.com/topic/architecture) — patterns apply in `commonMain`
- [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) — iOS target stable (1.8+)

---

## First session checklist

When sitting down to scaffold the project:

- [x] Create CMP project in this folder (`poke-counter-mobile/`)
- [x] Run on iOS Simulator, then physical iPhone
- [ ] Add Ktor + kotlinx.serialization
- [ ] Configure debug API base URL (simulator vs device)
- [ ] Implement enemy search screen only
- [ ] Add navigation stubs for moves and results (empty screens)
- [ ] Backend running locally with synced data

---

## Out of scope for early milestones

- Sharing Kotlin domain code with `poke-counter-backend` (possible later via KMP JVM/Native splits; not needed for v1)
- Offline search cache
- App Store / Play Store release pipelines
- Custom gestures before basic navigation works
- Compose Multiplatform Desktop or Web targets

---

## Repo layout (monorepo)

```
Pokemon-Calculator/
├── poke-counter-backend/    # Spring Boot API
├── poke-counter-frontend/   # Vue web client
└── poke-counter-mobile/     # Compose Multiplatform (this folder)
```

All three clients consume the same `/api/v1` API. CORS is irrelevant for native mobile apps.