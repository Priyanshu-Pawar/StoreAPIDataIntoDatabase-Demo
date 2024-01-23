package com.example.apicallstoredataindatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_data")
data class ApiData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val title: String,
    val brand: String
)
