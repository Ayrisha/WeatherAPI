# 🌤 Weather App — Приложение погоды

Простое Android-приложение на Kotlin, отображающее текущую погоду и прогноз на 5 дней с современным интерфейсом.

## Описание

Приложение позволяет:

- Показывать погоду города по умолчанию  
- Отображать список городов при отсутствии выбранного  
- Выбирать город и переходить к детальному прогнозу  
- Сохранять город как "по умолчанию"  
- Отображать температуру, описание, ветер, влажность  
- Показывать прогноз на 3–5 дней  
- Добавлять/удалять города из списка  

## Архитектура проекта

```text
app/
├── data/
│   ├── local/            # Локальное хранение (Room, DataStore)
│   ├── manager/          # SharedPrefManager
│   ├── network/          # API-запросы (Retrofit)
│   └── repository/       # Реализация репозиториев
│
├── di/
│   ├── ManagerModule     # DI для менеджеров
│   └── WeatherModule     # DI для погодных сервисов
│
├── domain/
│   ├── manager/          # Бизнес-логика
│   ├── model/            # Доменные модели
│   ├── repository/       # Абстракции репозиториев
│   └── usecases/         # UseCase'ы
│
├── presentation/
│   ├── city_list/        # Экран списка городов
│   ├── main_activity/    # MainActivity + ViewModel
│   ├── navgraph/         # Навигация
│   └── weather/          # Экран погоды
│
└── util/
    └── Constants         # Константы приложения
```
## Архитектурные решения

### 1. MVVM + Clean Architecture  
**Разделение на слои:**  

- **Data Layer**  
  Источник данных (API, Room, Preferences)  

- **Domain Layer**  
  Бизнес-логика (UseCases, Managers)  

- **Presentation Layer**  
  UI (ViewModel, Composable)  

### 2. Jetpack Compose  
- Полностью написано на Jetpack Compose
  
- Современный декларативный подход к UI

### 3. Hilt для Dependency Injection  
- Используется для внедрения зависимостей
  
- Упрощает управление жизненным циклом объектов
  
- Обеспечивает типобезопасность и разделение слоёв  

### 4. Retrofit + Gson  
- Запросы к weatherapi.com через Retrofit
  
- Парсинг ответа — Gson
  
- Использование Flow и suspend функций для реактивности  

### 5. Room / DataStore  
- **Room** используется для хранения списка сохранённых городов
  
- **DataStore** — для хранения текущего города  

### 6. Навигация  
- Реализована через `androidx.navigation:navigation-compose`
  
- Поддержка переходов между экранами
  
- Управление back stack  


## Использованные библиотеки

<div align="center">

| Библиотека          | Категория               | Назначение                          |
|---------------------|-------------------------|-------------------------------------|
| **Jetpack Compose** | UI Framework            | Современный декларативный UI        |
| **Hilt**            | Dependency Injection    | Внедрение зависимостей              |
| **Retrofit**        | Networking              | Работа с REST API                    |
| **Gson**           | Serialization           | Парсинг JSON-ответов                |
| **Room**           | Database                | Локальное хранение данных           |
| **Coroutines**     | Asynchronous Programming| Асинхронные операции                |
| **Navigation Compose** | Navigation           | Навигация между экранами            |
| **DataStore**      | Preferences             | Асинхронное хранение настроек       |

</div>
