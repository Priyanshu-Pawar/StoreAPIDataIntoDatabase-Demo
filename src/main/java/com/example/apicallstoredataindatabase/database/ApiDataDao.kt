package com.example.apicallstoredataindatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apicallstoredataindatabase.ApiData

@Dao
interface ApiDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApiDataIntoDatabase(api: ApiData)

    @Query("SELECT * FROM api_data")
    fun getAllApiData(): LiveData<List<ApiData>>
}