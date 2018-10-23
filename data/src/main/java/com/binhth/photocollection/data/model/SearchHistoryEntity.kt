package com.binhth.photocollection.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.SearchHistory


@Entity(tableName = "searchhistory")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val query: String = ""
) : BaseEntity()

class SearchHistoryMapper : EntityMapper<SearchHistory, SearchHistoryEntity> {
    override fun mapToDomain(entity: SearchHistoryEntity) = SearchHistory(
        query = entity.query
    )

    override fun mapToEntity(model: SearchHistory) = SearchHistoryEntity(
        id = 0,
        query = model.query
    )
}