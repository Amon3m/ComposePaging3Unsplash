package com.mon3m.paging3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mon3m.paging3.utils.Constant

@Entity(tableName = Constant.REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
