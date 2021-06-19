package app.klimatic.di.module

import app.klimatic.BuildConfig
import app.klimatic.data.remote.interceptors.AuthenticationInterceptor
import app.klimatic.data.remote.weather.CurrentWeatherService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "API_KEY"
const val BASE_URL = "BASE_URL"
const val AUTHENTICATION_INTERCEPTOR = "AUTHENTICATION_INTERCEPTOR"
const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"

val testNetworkModule = module {

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun provideAuthenticationInterceptor(
        apiKey: String
    ) = AuthenticationInterceptor(apiKey)

    fun provideOkHttpClient(
        authenticationInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    fun provideRetrofitClient(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideCurrentWeatherService(retrofit: Retrofit): CurrentWeatherService {
        return retrofit.create(CurrentWeatherService::class.java)
    }

    // Base Url
    single(qualifier = named(BASE_URL)) {
        "http://localhost:8080"
    }
    // Api Key
    single(qualifier = named(API_KEY)) {
        BuildConfig.API_KEY
    }

    // Authentication Interceptor
    single(qualifier = named(AUTHENTICATION_INTERCEPTOR)) {
        provideAuthenticationInterceptor(get(qualifier = named(API_KEY)))
    }

    // Logging Interceptor
    single(qualifier = named(LOGGING_INTERCEPTOR)) {
        provideHttpLoggingInterceptor()
    }

    // OkHttp Client
    single {
        provideOkHttpClient(
            get(qualifier = named(AUTHENTICATION_INTERCEPTOR)),
            get(qualifier = named(LOGGING_INTERCEPTOR))
        )
    }

    // Retrofit Client
    single {
        provideRetrofitClient(get(qualifier = named(BASE_URL)), get())
    }

    // Current Weather Service
    single {
        provideCurrentWeatherService(get())
    }
}
