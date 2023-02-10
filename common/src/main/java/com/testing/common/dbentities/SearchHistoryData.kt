package com.testing.common.dbentities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchHistory")
data class SearchHistoryData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val queryValue: String,
    val requestDate: String
)