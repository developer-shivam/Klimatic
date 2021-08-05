# Klimatic

**Klimatic** is an android app built using **Kotlin**. It try to showcase all the latest technologies used in android.

[![Test](https://github.com/developer-shivam/Klimatic/actions/workflows/test.yml/badge.svg)](https://github.com/developer-shivam/Klimatic/actions/workflows/test.yml)
[![Build](https://github.com/developer-shivam/Klimatic/actions/workflows/build.yml/badge.svg)](https://github.com/developer-shivam/Klimatic/actions/workflows/build.yml)

[![GitHub stars](https://img.shields.io/github/stars/developer-shivam/Klimatic)](https://github.com/developer-shivam/Klimatic/stargazers)
[![GitHub issues](https://img.shields.io/github/issues/developer-shivam/Klimatic)](https://github.com/developer-shivam/Klimatic/issues)
[![GitHub license](https://img.shields.io/github/license/developer-shivam/Klimatic)](https://github.com/developer-shivam/Klimatic/blob/master/LICENSE)

<a href="https://play.google.com/store/apps/details?id=app.klimatic"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height=60px /></a>

![](media/feature_graphic_asset.png)

## Built using
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Koin](https://insert-koin.io/) - A smart dependency injection library.
- [WaveView](https://github.com/developer-shivam/WaveView) - A custom view for wave animation.


## Architecture
App uses [MVVM (Model View View-Model)](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

## API
Klimatic uses <a href="https://www.weatherapi.com/" title="Free Weather API">WeatherAPI.com</a>. Create a API Key and update **apikey.properties**

    API_KEY="your-api-key"

## Contributors
[Shivam Satija](https://github.com/developer-shivam) and [Anshul Garg](https://github.com/garganshu)
