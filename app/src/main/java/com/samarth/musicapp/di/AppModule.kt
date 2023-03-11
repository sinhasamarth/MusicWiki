package com.samarth.musicapp.di

import com.samarth.musicapp.api.ApiService
import com.samarth.musicapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getRetrofit(client: OkHttpClient, gsonAdapter: GsonConverterFactory): Retrofit {
        val retrofit = Retrofit.Builder()
        retrofit.baseUrl(Constants.BASE_URL)
        retrofit.addConverterFactory(gsonAdapter)
        retrofit.client(client)
        return retrofit.build()
    }

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun getGsonAdapter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain -> return@addInterceptor addApiKeyToRequests(chain) }
            .build()
    }

    @Provides
    @Singleton
    fun addApiKeyToRequests(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url()
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", "0143bfb1f53a64771bff8ec273139724")
            .addQueryParameter("format", "json")
            .build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }

}