package com.example.weatherforecast.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.models.Weather
import com.example.weatherforecast.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun getWeather(city:String):DataOrException<Weather,Boolean,Exception>{
        return repository.getWeather(cityQuery = city)
    }


}


