package com.sample.basicapp.di

import com.sample.basicapp.util.AppUtils
import com.sample.basicapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    private lateinit var interceptor: HttpLoggingInterceptor
    private lateinit var okHttpClient: OkHttpClient

    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService {
        interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        interceptor.level = HttpLoggingInterceptor.Level.NONE

        okHttpClient = OkHttpClient.Builder()
            //TODO : Enable Log
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
        okHttpClient.dispatcher.maxRequestsPerHost = 1
        return Retrofit.Builder()
            .baseUrl(AppUtils.mBaseUrl)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }


//    @Provides
//    fun provideAuthRepository(backEndApi: ApiService): AuthRepository =
//        AuthRepository(backEndApi)
//
//    @Provides
//    fun provideAuthViewModel(
//        feedRepository: AuthRepository,
//        @ApplicationContext context: Context
//    ): AuthViewModel =
//        AuthViewModel(feedRepository)


}