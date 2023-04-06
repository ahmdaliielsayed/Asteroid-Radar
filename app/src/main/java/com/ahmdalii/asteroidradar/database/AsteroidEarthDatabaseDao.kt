package com.ahmdalii.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmdalii.asteroidradar.model.AsteroidEarth

@Dao
interface AsteroidEarthDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<AsteroidEarth>)

    @Query("SELECT * FROM asteroid_table")
    fun get(): LiveData<List<AsteroidEarth>>?

    @Query("SELECT * FROM asteroid_table  WHERE asteroidDate >= :week ORDER by asteroidDate ASC")
    fun getAllDataByWeek(week: String): LiveData<List<AsteroidEarth>>?

    @Query("SELECT * FROM asteroid_table WHERE asteroidDate == :today ORDER by asteroidDate ASC")
    fun getAllDataByToday(today: String): LiveData<List<AsteroidEarth>>?

    @Query("DELETE FROM asteroid_table")
    suspend fun clear()
}
