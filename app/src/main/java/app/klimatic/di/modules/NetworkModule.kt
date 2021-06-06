package app.klimatic.di.modules

import app.klimatic.BuildConfig
import app.klimatic.data.remote.CurrentWeatherService
import app.klimatic.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideBaseUrl() : String = BuildConfig.BASE_URL

    @ApplicationScope
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideRetrofitClient(baseUrl: String, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideCurrentWeatherService(retrofit: Retrofit) : CurrentWeatherService {
        return retrofit.create(CurrentWeatherService::class.java)
    }
}