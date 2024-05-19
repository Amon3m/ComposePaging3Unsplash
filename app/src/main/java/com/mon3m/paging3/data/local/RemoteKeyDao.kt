package com.mon3m.paging3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mon3m.paging3.model.UnsplashRemoteKeys
import com.mon3m.paging3.utils.Constant.REMOTE_KEYS_TABLE

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<UnsplashRemoteKeys>)

    @Query("SELECT * FROM $REMOTE_KEYS_TABLE WHERE id = :id")
    suspend fun remoteKeysId(id: String): UnsplashRemoteKeys?
    @Query("DELETE FROM $REMOTE_KEYS_TABLE")
    suspend fun clearRemoteKeys()
}