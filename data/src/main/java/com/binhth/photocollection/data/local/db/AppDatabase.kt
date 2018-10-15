package com.binhth.photocollection.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binhth.photocollection.data.local.db.dao.CollectionDao
import com.binhth.photocollection.data.model.CollectionEntity

@Database(entities = [CollectionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun collectionDao(): CollectionDao
}