package com.binhth.photocollection.di

import android.content.Context
import com.tonyodev.fetch2.Fetch
import com.tonyodev.fetch2.FetchConfiguration
import com.tonyodev.fetch2okhttp.OkHttpDownloader
import okhttp3.OkHttpClient
import org.koin.dsl.module.module


val fetchModule = module(override = true) {
    single { createAppFetchConfig(get(), get()) }
    single { createAppFetch(get()) }
}


fun createAppFetchConfig(okHttpClient: OkHttpClient, context: Context): FetchConfiguration {
    return FetchConfiguration.Builder(context)
        .setDownloadConcurrentLimit(10)
        .setHttpDownloader(OkHttpDownloader(okHttpClient))
        .build()
}

fun createAppFetch(fetchConfiguration: FetchConfiguration): Fetch {
    return Fetch.getInstance(fetchConfiguration)

}




