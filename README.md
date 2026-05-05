<h1 align="center">🚀 News App</h1>

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=kotlin,androidstudio,materialui,gradle" />
  </a>
</p>

---

## 📌 Overview

**News App** is a modern Android news reader application that fetches live technology from external APIs and presents a smooth article-reading experience, compose-based browsing and news search. It focuses on high performance and maintainability through a reactive, clean architecture layout.

---

## ✨ Features

| Feature | Description |
|---|---|
| 📡 **Live News Fetching** | Integrates with **NewsAPI** using Ktor for real-time article retrieval. |
| 🔍 **Dynamic Search** | Provides a keyword-based search flow with dedicated UI and query handling. |
| 🏗️ **Clean Data Pipeline** | Uses DTO-to-domain mapping to isolate API models from UI logic. |
| 📍 **Typed Navigation** | Compose-based routing across Home, Search, Settings, and Detail screens. |
| 📱 **Rich Consumption UI** | Features pull-to-refresh, swipe gestures, and Coil-powered image loading. |
| 📖 **In-App Reading** | Opens full content inside a WebView-based detail flow for continuous reading. |

---

## 🏛️ Architecture

The project follows a **Clean Architecture-inspired** layered structure with feature-based packaging to ensure code maintainability.
```text
Presentation Layer  →  Jetpack Compose UI, ViewModels, State Handling, Navigation
       ↓
Domain Layer        →  Use Cases, Repository Contracts, Domain Models
       ↓
Data Layer          →  Repository Impl, Remote Data Sources, DTOs, Mappers
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Kotlin (Java 17 target runtime) |
| **UI Framework** | Jetpack Compose + Material 3 |
| **Networking** | Ktor Client + Kotlinx Serialization |
| **DI Framework** | Koin (Core + Android + Compose) |
| **Image Loading** | Coil Compose |
| **UI Components** | ToogleButton (via JitPack: `com.github.Nirdhant`) |

---

## 🔌 External API Integration

The app consumes external news services to provide real-time content.

> **Base Provider:** [NewsAPI.org](https://newsapi.org/v2/)

| Endpoint | Purpose |
|---|---|
| `/top-headlines` | Fetches live technology headlines. |
| `/everything` | Renders search-specific article results based on user input. |

---

## 📂 Project Structure
```text
app/src/main/java/com/example/newsapp/
├── data/           → Remote data sources, DTOs, and repository implementation
├── domain/         → Repository interfaces, domain models, and use cases
├── presentation/   → Compose screens, UI components, and state management
│   ├── home/
│   ├── search/
│   ├── detail/
│   └── navigation/ → App Navigation Host and route definitions
└── NewsApp.kt      → Application class for Koin initialization
```

---

## 🚦 Navigation Flow

1. **MainActivity**: Sets the Compose content and starts the navigation graph.
2. **Bottom Navigation**: Switch between **Home**, **Search**, and **Settings** routes.
3. **Article Selection**: Leads to the **Detail Screen**, passing article data to the WebView-based flow.