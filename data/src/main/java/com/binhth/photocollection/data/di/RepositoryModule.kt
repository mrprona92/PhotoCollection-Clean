package com.binhth.photocollection.data.di

import android.content.Context
import androidx.room.Room
import com.binhth.photocollection.data.CollectionRepositoryImpl
import com.binhth.photocollection.data.Constants
import com.binhth.photocollection.data.local.db.AppDatabase
import com.binhth.photocollection.domain.repository.CollectionRepository
import com.google.gson.Gson
import org.koin.dsl.module.module

val repositoryModule = module(override = true) {
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createCollectionDao(get()) }
    single { Gson() }
    single<CollectionRepository> { CollectionRepositoryImpl(get(), get()) }
}

fun createDatabaseName() = Constants.DATABASE_NAME

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

fun createCollectionDao(appDatabase: AppDatabase) = appDatabase.collectionDao()
