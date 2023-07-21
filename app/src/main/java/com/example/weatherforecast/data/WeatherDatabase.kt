package com.example.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecast.models.Favorite
import com.example.weatherforecast.models.Unit

@Database(entities = [Favorite::class, Unit::class], version = 3, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}