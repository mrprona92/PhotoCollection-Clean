package com.binhth.photocollection.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binhth.photocollection.data.model.SearchHistoryEntity
import io.reactivex.Single

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listSearched: List<SearchHistoryEntity>): List<Long>

    @Query("SELECT * FROM searchhistory")
    fun findSearchHistory(): Single<List<SearchHistoryEntity>>
}