# Weather App

A simple Android weather application that lets you search for current weather conditions by city name. The app fetches data from the [OpenWeatherMap API](https://openweathermap.org/api) and displays temperature, feels-like temperature, min/max temperatures, and humidity.

## Features

- Search for weather by city name
- Displays temperature in Celsius
- Shows feels-like temperature, minimum and maximum temperature, and humidity

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (or any Android SDK)
- An OpenWeatherMap API key (free tier available at [openweathermap.org](https://openweathermap.org/api))

### Building

1. Clone the repository
2. Open the project in Android Studio
3. Build and run on an emulator or device

## Project Structure

```
Weather_App/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/weather_app/
│   │   │   └── MainActivity.java       # Main activity with weather API integration
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml  # UI layout
│   │   │   └── values/
│   │   │       └── strings.xml         # String resources
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts                # App-level build configuration
├── build.gradle.kts                   # Project-level build configuration
└── gradle/
    └── wrapper/                        # Gradle wrapper files
```

## Technologies Used

- Java
- Android SDK
- [OpenWeatherMap API](https://openweathermap.org/api)
