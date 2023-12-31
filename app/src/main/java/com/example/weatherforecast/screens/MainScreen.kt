package com.example.weatherforecast.screens

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.models.Weather
import com.example.weatherforecast.models.WeatherItem
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.utils.formatDate
import com.example.weatherforecast.utils.formatDecimals
import com.example.weatherforecast.widgets.HumidityWindPressureRow
import com.example.weatherforecast.widgets.WeatherAppBar
import com.example.weatherforecast.widgets.WeatherDetailRow
import com.example.weatherforecast.widgets.sunriseandsunset
import java.lang.Exception


@Composable
fun MainScreen(navController: NavController,
               viewModal: viewModel= hiltViewModel(),
               settingsViewModel: SettingsViewModel= hiltViewModel(),
               city: String?) {
    val curCity:String=if(city!!.isBlank()) "Delhi" else city
    val unitFromDb=settingsViewModel.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("imperial")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }
       if(!unitFromDb.isNullOrEmpty()) {
           unit = unitFromDb[0].unit.split(" ")[0].lowercase()
       }
        isImperial=unit=="imperial"
        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ) {
            value = viewModal.getWeather(curCity,unit=unit)
        }.value
        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(navController, weather = weatherData.data!!,isImperial)
            saveCityToSharedPreferences(LocalContext.current, curCity)
        }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(navController: NavController, weather: Weather, isImperial: Boolean) {
    Scaffold(topBar = {
        Surface(shadowElevation = 5.dp) {
            WeatherAppBar(
                title = weather.city.name + " ,${weather.city.country}",
                navController = navController,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                }
            )
        }
    }) {
        MainContent(modifier = Modifier.padding(it), data = weather,isimperial=isImperial)
    }

}

@Composable
fun MainContent(data: Weather, modifier: Modifier, isimperial: Boolean) {

    val weatherItem = data.list[0]
    val imageurl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = formatDate(weatherItem.dt), // Wed Nov 30
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageurl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "°",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        HumidityWindPressureRow(weather = weatherItem,isimperial)
        Divider()
        sunriseandsunset(weather = weatherItem)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)
                }
            }
        }
    }
}

@Composable
fun WeatherStateImage(imageurl: String) {
    Image(
        painter = rememberImagePainter(imageurl),
        contentDescription = "Icon Image",
        modifier = Modifier.size(80.dp)
    )
}

fun saveCityToSharedPreferences(context: Context, city: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "WeatherForecastPrefs",
        Context.MODE_PRIVATE
    )
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("City", city)
    editor.apply()
}

