package app.klimatic.di.modules

import app.klimatic.BuildConfig
import app.klimatic.data.remote.CurrentWeatherService
import app.klimatic.di.qualifiers.ApiKey
import app.klimatic.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    @ApiKey
    fun provideApiKey(): String = BuildConfig.API_KEY

    @ApplicationScope
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @ApplicationScope
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @ApplicationScope
    @Provides
    fun provideAuthenticationInterceptor(@ApiKey apiKey: String) = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", apiKey)
                .build()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(authenticationInterceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideRetrofitClient(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideCurrentWeatherService(retrofit: Retrofit): CurrentWeatherService {
        return retrofit.create(CurrentWeatherService::class.java)
    }
}