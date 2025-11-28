# Newly - News Aggregation Android App

A modern Android news application built with Clean Architecture, Jetpack Compose, and MVI pattern.

## Overview

Newly is a news aggregation app that allows users to browse top news, popular news, news by category, search for specific articles, and view detailed news content. The application demonstrates modern Android development best practices with a multi-module architecture.

## Architecture

The project follows **Clean Architecture** principles with clear separation of concerns across three main layers:

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (feature - UI, ViewModels, MVI)        │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         Domain Layer                    │
│  (Business Logic, Use Cases, Models)    │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         Data Layer                      │
│  (Repository, API, Database, Cache)     │
└─────────────────────────────────────────┘
```

### Module Dependencies

```
app → feature, domain, data
feature → domain
data → domain
domain → (no external module dependencies)
```

## Modules

### 1. Domain Module

**Purpose:** Core business logic and use cases (framework-agnostic)

**Key Components:**
- **Repository Interface:** `NewsRepository` - Contract for data access
- **Domain Models:**
  - `New` - Core news entity
  - `Category` - News categories (HOME, BUSINESS, HEALTH, SCIENCE, SPORTS, TECH)
  - `ListType` - Type information
- **Use Cases (8):**
  - `GetTopNewsUseCase` - Fetch top news
  - `GetPopularNewsUseCase` - Fetch popular news
  - `GetCategoryNewsUseCase` - Get paginated news by category
  - `SearchNewsUseCase` - Search news by query
  - `GetNewUseCase` - Get single news article by ID
  - `RefreshTopNewsUseCase` - Refresh top news from API
  - `RefreshPopularNewsUseCase` - Refresh popular news from API
- **Error Handling:**
  - `AppError` interface with `GeneralError`, `NetworkError`, `NewNotFoundError`
  - Custom exceptions for network operations

**Dependencies:**
- result4k (functional error handling)
- Jetpack Paging
- Hilt (DI)

### 2. Data Module

**Purpose:** Data access and management layer

**Architecture Pattern:** Repository + Data Source + Data Store

**Key Components:**

#### Repository Implementation
- `NewsRepositoryImpl` - Implements domain repository interface
  - Coordinates between API and local database
  - Handles pagination with `Pager` and `RemoteMediator`
  - Error mapping and result wrapping

#### Data Sources (Remote)
- `NewsApiDataSourceImpl` - API integration
  - 4 endpoints: Popular, Top, Category, Search
  - Error handling for rate limiting (HTTP 429) and network errors

#### Data Stores (Local - Room)
- `NewsRoomDataStoreImpl` - Local database operations
  - Caching for top/popular news
  - Pagination state tracking for category news

#### API Layer
- `NewsApi` - Retrofit interface (4 endpoints)
- `AuthInterceptor` - API authentication

#### Database (Room)
- `AppDatabase` - Room configuration
  - Entities: `NewEntity`, `NewEntryEntity`, `NewsPaginationStateEntity`
  - DAOs: `NewsDao`, `NewsEntriesDao`, `NewsPaginationDao`

#### Paging
- `CategoryNewsRemoteMediator` - Smart pagination with 1-hour cache
- `SearchNewsPagingSource` - Search results pagination

#### DTOs & Mappers
- Response models: `PopularNewsResponse`, `TopNewsResponse`, `SearchNewsResponse`
- Mappers for DTO → Entity → Domain model conversion

**Dependencies:**
- domain module
- Retrofit, GSON (networking)
- Room (local database)
- Jetpack Paging
- Hilt (DI)

### 3. Feature Module

**Purpose:** Presentation layer with UI and ViewModels

**Architecture Pattern:** Model-View-Intent (MVI) with Jetpack Compose

**Screens:**

#### Onboarding
- Entry screen before main app

#### Home
- Main hub with bottom navigation
- HorizontalPager for News and Bookmarks tabs
- Components: TopBar, BottomNavBar

#### For You (News Feed)
- Displays top and popular news sections
- `ForYouViewModel` with intent-based actions
- Pull-to-refresh functionality
- Concurrent data loading

#### News by Category
- Browse news by category
- Category selection UI
- Paginated content per category

#### Search
- Real-time search with debounce
- Paginated search results
- `SearchViewModel` with reactive Flow-based search

#### Detail
- Full article view
- State management with sealed classes
- `DetailViewModel` for article loading

#### Bookmarks
- Saved articles view

**Shared Components:**
- `NewItem` - Reusable news card widget
- `NewsList` - Paginated news list component
- Theme configuration (Color, Typography, Theme)
- Navigation routes and utilities
- Extensions for Flow and Modifier

**Dependencies:**
- domain module
- Jetpack Compose BOM
- Compose Navigation
- Lifecycle (ViewModel, StateFlow)
- Coil (image loading)
- Hilt (DI)

### 4. App Module

**Purpose:** Application orchestration and dependency aggregation

**Key Components:**
- `NewlyApp` - Hilt application class
- `NewlyActivity` - Main entry point
- `NewlyNavGraph` - Root Compose navigation
  - Flow: Onboarding → Home → Search/Detail/Bookmarks

## Technology Stack

### Core Architecture
- **Clean Architecture** - Separation of concerns
- **MVI Pattern** - Unidirectional data flow
- **Multi-module** - Independent, reusable modules

### Android Jetpack
- **Compose** - Modern declarative UI
- **Navigation** - Type-safe navigation
- **Paging 3** - Efficient pagination
- **Room** - Local database
- **Lifecycle** - ViewModel, StateFlow
- **Hilt** - Dependency injection

### Networking & Data
- **Retrofit 2** - REST API client
- **GSON** - JSON serialization
- **Kotlin Coroutines** - Asynchronous operations
- **Flow** - Reactive streams

### UI & Images
- **Material 3** - Design system
- **Coil** - Image loading and caching

### Error Handling
- **Result4k** - Functional Result types

## Key Features

- Browse top news and popular news
- Explore news by category (Business, Health, Science, Sports, Tech)
- Search news articles with real-time results
- View detailed news articles
- Bookmark articles (planned)
- Offline-first with smart caching
- Pull-to-refresh
- Infinite scroll pagination
- Error handling and retry mechanisms

## Data Flow

```
API → NewsDataSource → Repository → Room Database
                    ↓                    ↓
              Use Cases (Domain)    Local Cache
                    ↓
          ViewModels (Feature)
                    ↓
            Compose UI
```

## Caching Strategy

- **Room Database** as single source of truth
- **RemoteMediator** for smart cache invalidation
  - 1-hour cache timeout for category news
  - Automatic refresh on cache expiration
- **Pagination State Tracking** for incremental loads
- Top and Popular news cached separately

## Error Handling

- **Functional Approach** with Result4k
- **Custom Error Types:**
  - `GeneralError` - Generic failures
  - `NetworkError` - Network-related issues
  - `NewNotFoundError` - Article not found
- **UI Error States** - User-friendly error messages
- **Retry Mechanisms** - Pull-to-refresh for recovery

## State Management

- **ForYouViewModel:** MutableStateFlow with explicit Intent actions
- **SearchViewModel:** Flow-based reactive search with debounce
- **DetailViewModel:** Sealed state classes (Idle, Success, Error)
- **Unidirectional Data Flow** following MVI pattern

## Testing

The project includes:
- **Unit Tests** - Repository and API layer
- **Instrumented Tests** - Room database operations
- **Compose Tests** - Basic UI testing with paging

## Project Structure

```
Newly/
├── app/                       # Application module
│   ├── NewlyApp.kt           # Hilt application
│   ├── NewlyActivity.kt      # Main activity
│   └── navigation/           # Root navigation graph
├── domain/                    # Business logic layer
│   ├── NewsRepository.kt     # Repository interface
│   ├── models/               # Domain models
│   ├── usecases/             # Use cases
│   ├── errors/               # Error definitions
│   └── DomainDI.kt          # Dependency injection
├── data/                      # Data access layer
│   ├── NewsRepositoryImpl.kt # Repository implementation
│   ├── api/                  # Retrofit API
│   ├── dao/                  # Room DAOs
│   ├── datasources/          # API data sources
│   ├── datastores/           # Local data stores
│   ├── dto/                  # Data transfer objects
│   ├── entities/             # Room entities
│   ├── mappers/              # Data mappers
│   ├── paging/               # Pagination logic
│   └── di/                   # Dependency injection
└── feature/                   # Presentation layer
    ├── onboarding/           # Onboarding screen
    ├── home/                 # Home hub screen
    ├── foryou/               # News feed screen
    ├── news/                 # Category news screen
    ├── search/               # Search screen
    ├── detail/               # Article detail screen
    ├── bookmark/             # Bookmarks screen
    └── shared/               # Shared UI components
        ├── widgets/          # Reusable widgets
        ├── theme/            # App theming
        ├── extensions/       # Kotlin extensions
        └── NavigationRoute.kt # Navigation utilities
```

## Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17 or higher
- Android SDK with API 24+ (target API 34)

### Setup
1. Clone the repository
2. Open project in Android Studio
3. Add your News API key in `local.properties`:
   ```
   NEWS_API_KEY=your_api_key_here
   ```
4. Sync Gradle
5. Run the app

## Build Configuration

- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Compile SDK:** 34
- **Kotlin:** 2.0.21
- **Gradle:** 8.7

## License

[Your license information here]

## Contributors

[Your contributor information here]
