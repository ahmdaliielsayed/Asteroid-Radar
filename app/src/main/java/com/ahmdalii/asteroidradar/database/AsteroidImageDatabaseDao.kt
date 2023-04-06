package com.ahmdalii.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmdalii.asteroidradar.model.AsteroidImageResponse

@Dao
interface AsteroidImageDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: AsteroidImageResponse)

    @Query("SELECT * FROM image_table")
    fun get(): LiveData<AsteroidImageResponse>?

    @Query("DELETE FROM image_table")
    suspend fun clear()
}
