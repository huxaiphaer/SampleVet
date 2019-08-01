package com.huxy.samplevet.data.offline.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huxy.samplevet.data.offline.Coordinates

@Dao
interface CoordinatesDao {

    @get:Query("SELECT * from coordinates")
    val allPlaces: LiveData<List<Coordinates>>

    @Insert
    fun insert(coordinates: Coordinates)

    @Query("DELETE FROM coordinates where mId =:mId")
    fun deleteSingle(mId: Int)
}
