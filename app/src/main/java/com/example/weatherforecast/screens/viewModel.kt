package com.example.weatherforecast.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.models.Weather
import com.example.weatherforecast.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class viewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun getWeather(city: String, unit: String):DataOrException<Weather,Boolean,Exception>{
        Log.d("TAG", "getWeather: ")
      val x= repository.getWeather(cityQuery = city,unit=unit)
        Log.d("viewmodel", x.data?.list?.size.toString())
        Log.d("viewmodel", x.e?.message.toString())
        return x
    }


}


