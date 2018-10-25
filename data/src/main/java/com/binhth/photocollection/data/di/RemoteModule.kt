package com.binhth.photocollection.data.di

import com.binhth.photocollection.data.BuildConfig
import com.binhth.photocollection.data.Constants
import com.binhth.photocollection.data.remote.api.PhotoCollectionApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val remoteModule = module(override = true) {
    single { createLoggingInterceptor() }
    single { createHeaderInterceptor() }
    single { createOkHttpClient(get(), get()) }
    single { createAppRetrofit(get()) }
    single { createPhotoCollectionApi(get()) }
}

fun createLoggingInterceptor(): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
    return logging
}

fun createOkHttpClient(
    logging: Interceptor,
    header: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(header)
        .addInterceptor(logging)
        .build()
}

fun createHeaderInterceptor(
): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .addQueryParameter(Constants.CLIENT_ID, Constants.ACCESS_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .header("Content-Type", "application/json")
            .method(request.method(), request.body())
            .build()
        chain.proceed(newRequest)
    }
}

fun createPhotoCollectionApi(retrofit: Retrofit): PhotoCollectionApi {
    return retrofit.create(PhotoCollectionApi::class.java)
}

fun createAppRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}





