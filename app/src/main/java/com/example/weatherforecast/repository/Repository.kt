package com.example.weatherforecast.repository

import android.util.Log
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.models.Weather
import com.example.weatherforecast.network.WeatherApi
import javax.inject.Inject
import kotlin.math.log

class Repository @Inject constructor(private val api:WeatherApi) {
    suspend fun getWeather(cityQuery: String, unit: String):DataOrException<Weather,Boolean,Exception>{
          val response=try {
              api.getWeather(query = cityQuery,unit=unit)

          }catch (e:Exception){

              return DataOrException(e=e)
          }
        return DataOrException(data = response)
    }
}