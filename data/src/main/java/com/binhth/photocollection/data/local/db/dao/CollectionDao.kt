package com.binhth.photocollection.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binhth.photocollection.data.model.CollectionRoomEntity
import io.reactivex.Single

@Dao
interface CollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(collectionEntity: CollectionRoomEntity)

    @Query("SELECT * FROM collection WHERE id = :id")
    fun findById(id: String): Single<CollectionRoomEntity>
}