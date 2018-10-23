package com.binhth.photocollection.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binhth.photocollection.data.local.db.dao.CollectionDao
import com.binhth.photocollection.data.local.db.dao.SearchDao
import com.binhth.photocollection.data.model.CollectionRoomEntity
import com.binhth.photocollection.data.model.SearchHistoryEntity

@Database(entities = [CollectionRoomEntity::class, SearchHistoryEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun collectionDao(): CollectionDao

    abstract fun searchDao(): SearchDao
}