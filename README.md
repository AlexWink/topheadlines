# Top Headlines

This project demonstrates various Android development skills, highlighting a **Model-View-ViewModel (MVVM)** architecture and modern best practices. While the design and functionality are intentionally minimal, this app serves as a foundation for creating a more feature-rich and polished real-world application.

## Prerequisites

1. Obtain an API key from [News API](https://newsapi.org/).
2. Copy the `apikeys.properties.example` file and rename it to `apikeys.properties`.
3. Add your News API key to the `apikeys.properties` file.

## Features

- Fetch and display top headlines using the News API.
- Basic navigation and UI components powered by **Jetpack Compose**.
- Modern Android libraries and development patterns.

## Architecture

This app follows the **Model-View-ViewModel (MVVM)** architecture, widely recommended for Android
projects. Architectural highlights include:

- **Library Selection**: Preference for official or officially recommended libraries by Google and
  JetBrains for better long-term support. For gaps, stable and widely used Android libraries are
  used.
- **Layering**: A simplified architecture tailored to the project’s scope while maintaining good
  code organization.
- **Inspiration**: Concepts and approaches derived from open-source projects
  like [Now In Android](https://github.com/android/nowinandroid).

> **Exploration**: While experimenting with libraries like **Decompose** and **Voyager** in other
> projects, this app adopts a standard approach to align with industry best practices.

## API Bridge

A custom `ApiBridge` was implemented, reusing concepts from my professional work. Key decisions
include:

- **Pagination**: Set `pageSize` to 100 for simplicity, with the option to optimize by reducing it
  to 20 and implementing infinite scrolling.
- **Result Class**: Used for transferring data from the network layer to the ViewModel, leveraging
  coroutines for asynchronous operations.
- **Source Filtering**: Excluded the "google-news" source due to lower data quality.

> **Optimization Opportunity**: Sources are fetched with every request. Implementing caching could
> reduce unnecessary API calls.

## Error Management

Currently, error handling is basic and lacks localization. For production, robust and user-friendly
error management is recommended.

## Testing

### Unit Tests

- Verifies that UI states reflect data correctly.
- Utilizes `MainDispatcherRule` with a custom `UnconfinedTestDispatcher`.
- Simulates HTTP requests with delays in `fetchArticles()` for realistic test behavior.
- **Limitations**: Mocking `SavedStateHandle` was not resolved, so `articleId` is passed as a
  parameter to the ViewModel.

### Instrumented Tests

- Validates UI components and navigation behavior.
- Uses a `TestTopHeadlinesApp` to inject test dependencies.

## Libraries Used

- **Kotlinx (JetBrains)**:
    - Serialization
    - Coroutines
- **Ktor**: Lightweight library for network operations.
- **Compose Navigation**: Enables typed and declarative navigation.
- **Material 3**: Modern Material Design 3 components with a custom theme generated using the
  Material 3 Theme Builder.
- **Coil**: Efficient image loading library.
- **Koin**: Dependency injection framework.

## Limitations and Future Improvements

1. **Experimental APIs**: Replace experimental APIs with stable alternatives for production use.
2. **Additional Features**:
    - Pull-to-refresh for better user experience.
    - In-app country selection for customized news feeds.
    - Detailed article metadata display (e.g., date, author, source).
3. **Error Management**: Implement robust and localized error handling.
4. **API Optimization**: Cache sources to reduce unnecessary API calls.

## Time Spent

Approximately **10 hours** were invested in developing this project, including setup,
implementation, and testing.

> **Note**: ChatGPT was used to format the README and improve the english syntax.
