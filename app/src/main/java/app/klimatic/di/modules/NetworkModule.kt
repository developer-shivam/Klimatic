package app.klimatic.di.modules

import app.klimatic.BuildConfig
import app.klimatic.data.remote.interceptors.AuthenticationInterceptor
import app.klimatic.data.remote.service.SearchService
import app.klimatic.data.remote.service.WeatherService
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideAuthenticationInterceptor() }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofitClient(get()) }
    single { provideCurrentWeatherService(get()) }
    single { provideSearchService(get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
}

private fun provideAuthenticationInterceptor(): Interceptor {
    return AuthenticationInterceptor(BuildConfig.API_KEY)
}

private fun provideOkHttpClient(
    authenticationInterceptor: Interceptor,
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(authenticationInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideCurrentWeatherService(retrofit: Retrofit): WeatherService {
    return retrofit.create(WeatherService::class.java)
}

private fun provideSearchService(retrofit: Retrofit): SearchService {
    return retrofit.create(SearchService::class.java)
}
