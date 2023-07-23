# Weather Forecast App


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Screenshots](#screenshots)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Acknowledgements](#acknowledgements)

## Introduction

Welcome to the Weather Forecast App! This app provides real-time weather forecasts for different cities around the world. It is designed to give users up-to-date weather information to help them plan their day and stay prepared for any weather conditions.

The Weather Forecast App offers a clean and intuitive user interface built using Jetpack Compose, making it easy for users to interact with the app and access weather details with just a few taps.

**Architecture:** The Weather Forecast App follows the MVVM (Model-View-ViewModel) architectural pattern combined with the Clean Architecture approach. It separates the UI, business logic, and data into distinct layers, allowing for a modular and scalable app structure.

## Features

The Weather Forecast App comes with the following features:

- **City Search:** Users can search for weather forecasts of different cities by entering the city name in the search bar. The app provides autocomplete suggestions to help users find the correct city.

- **Current Weather Details:** Upon selecting a city, users can view the current weather details for that location, including temperature, weather conditions (e.g., sunny, cloudy, rainy), humidity, wind speed, and atmospheric pressure.

- **Sunrise and Sunset Times:** The app displays the sunrise and sunset times for the selected city, helping users plan their activities accordingly.

- **Favorite Cities:** Users can mark cities as favorites to quickly access their weather forecasts without the need to search for them again.

- **Unit Conversion:** The Weather Forecast App supports both metric and imperial units for temperature, wind speed, and atmospheric pressure. Users can switch between these units based on their preferences.

## Screenshots
<div style="display: flex; flex-direction: row; justify-content: space-between">
<img src="https://github.com/geenik/Weather-Forecast/assets/92754155/7fc88540-db64-43c4-afe8-8be1f085c0f5" width="180" height="350">
<img src="https://github.com/geenik/Weather-Forecast/assets/92754155/69f707e8-1e0b-4e5f-973e-2b1b5b9c177b" width="180" height="350">
<img src="https://github.com/geenik/Weather-Forecast/assets/92754155/2bb539c9-8c5f-49f5-82a9-48adaa0beef3" width="180" height="350">
<img src="https://github.com/geenik/Weather-Forecast/assets/92754155/def60c8d-f570-485d-a509-1fb9be90c950" width="180" height="350">
</div>

## Technologies

The Weather Forecast App is built using the following technologies and libraries:

1. **Home Screen:** The home screen of the app displays the current weather information for a default city (e.g., Delhi). To search for weather forecasts of other cities, click on the search icon in the app bar.

2. **Search Screen:** The search screen allows users to enter the name of the city they want to search for. As users type, the app provides autocomplete suggestions. Tap on a city to view its weather details.

3. **Weather Details Screen:** The weather details screen displays the current weather information for the selected city. It includes temperature, weather conditions, humidity, wind speed, atmospheric pressure, sunrise and sunset times.

4. **7-Day Forecast:** Swipe left on the weather details screen to access the 7-day forecast, showing the weather conditions for the next week.

5. **Favorite Cities:** To mark a city as a favorite, tap on the star icon next to the city name on the weather details screen. The favorite cities can be accessed quickly from the home screen.

6. **Unit Conversion:** To switch between metric and imperial units, go to the app's settings and select the desired unit system. The app will update the weather details accordingly.

## Getting Started

To run the Weather Forecast App locally, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/weather-forecast-app.git`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or a physical device.

Ensure you have the latest version of Android Studio and the Android SDK installed.

## Usage

1. **Home Screen:** The home screen of the app displays the current weather information for a default city (e.g., Delhi). To search for weather forecasts of other cities, click on the search icon in the app bar.

2. **Search Screen:** The search screen allows users to enter the name of the city they want to search for. As users type, the app provides autocomplete suggestions. Tap on a city to view its weather details.

3. **Weather Details Screen:** The weather details screen displays the current weather information for the selected city. It includes temperature, weather conditions, humidity, wind speed, atmospheric pressure, sunrise and sunset times.

4. **Favorite Cities:** To mark a city as a favorite, tap on the star icon next to the city name on the weather details screen. The favorite cities can be accessed quickly from the home screen.

5. **Unit Conversion:** To switch between metric and imperial units, go to the app's settings and select the desired unit system. The app will update the weather details accordingly.


## Acknowledgements

The Weather Forecast App would not have been possible without the following resources and contributions:

- The weather data is provided by OpenWeatherMap API (https://openweathermap.org).
