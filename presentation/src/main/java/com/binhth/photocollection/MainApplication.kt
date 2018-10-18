package com.binhth.photocollection

import android.app.Application
import com.binhth.photocollection.data.di.mapperModule
import com.binhth.photocollection.data.di.remoteModule
import com.binhth.photocollection.data.di.repositoryModule
import com.binhth.photocollection.di.appModule
import com.binhth.photocollection.di.itemMapperModule
import com.binhth.photocollection.di.viewModelModule
import com.binhth.photocollection.domain.di.useCaseModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this, listOf(
                mapperModule,
                remoteModule,
                repositoryModule,
                useCaseModule,
                appModule,
                itemMapperModule,
                viewModelModule
            )
        )
    }
}