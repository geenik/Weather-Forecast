package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.screens.AboutScreen
import com.example.weatherforecast.screens.FavoriteScreen
import com.example.weatherforecast.screens.MainScreen
import com.example.weatherforecast.screens.SearchScreen
import com.example.weatherforecast.screens.SettingsScreen
import com.example.weatherforecast.screens.WeatherSplashScreen
import com.example.weatherforecast.screens.viewModel


@Composable
fun WeatherNavigation(){
    val navController
    = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
      composable(WeatherScreens.SplashScreen.name){
          WeatherSplashScreen(navController=navController)
      }
        val route=WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name="city"){
                    type= NavType.StringType
                }
            )
        ){
            it.arguments?.getString("city").let {
                val viewModal= hiltViewModel<viewModel>()
                MainScreen(navController=navController, viewModal = viewModal,city=it)
            }

        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController=navController)
        }
        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }
    }
}