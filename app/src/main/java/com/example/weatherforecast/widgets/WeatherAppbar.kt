package com.example.weatherforecast.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.models.Favorite
import com.example.weatherforecast.navigation.WeatherScreens
import com.example.weatherforecast.screens.FavoriteViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel= hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val showdialog = remember {
        mutableStateOf(false)
    }
    if (showdialog.value) {
        ShowSettingDropDownMenu(showdialog = showdialog, navController = navController)
    }
    val showIt = remember {
        mutableStateOf(false)
    }
    val context= LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = title, color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Button"
                    )
                }
                IconButton(onClick = {
                    showdialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More Icon"
                    )
                }
            } else {
                Box {}
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    }
                )
            }
            if(isMainScreen){
                val isfavorite=favoriteViewModel.favList.collectAsState().value.filter {
                    it.city==title.split(",")[0]
                }
                if(isfavorite.isEmpty()){
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite icon",
                        modifier = Modifier
                            .scale(0.9f)
                            .clickable {
                                val datalist = title.split(",")
                                favoriteViewModel.insertFavorite(
                                    Favorite(
                                        city = datalist[0],
                                        country = datalist[1]
                                    ),
                                ).run {
                                    showIt.value=true
                                }
                            },
                        tint = Color.Red.copy(alpha = 0.6f)

                    )
                }else {
                    showIt.value=false
                    Box {}
                }
                ShowToast(context = context, showIt = showIt)
            }
                }
         ,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            Color.Transparent
        )
    )
}

@Composable
fun ShowSettingDropDownMenu(showdialog: MutableState<Boolean>, navController: NavController) {
    val expanded = remember {
        mutableStateOf(true)
    }
    val items = listOf("About", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false
                               showdialog.value=false},
            modifier = Modifier
                .width(140.dp)
                .background(
                    Color.White
                )
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(text = {
                    Text(
                        text = text,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                when(text){
                                    "About"-> WeatherScreens.AboutScreen.name
                                    "Favorites"->WeatherScreens.FavoriteScreen.name
                                    else ->WeatherScreens.SettingsScreen.name
                                }
                            )
                        },
                        fontWeight = FontWeight.W300
                    )
                }, leadingIcon = {
                                Icon( when(text){
                                     "About"-> Icons.Default.Info
                                     "Favorites"->Icons.Default.FavoriteBorder
                                     else ->Icons.Default.Settings
                                 },contentDescription = null)
                }
                    ,onClick = {
                    expanded.value = false
                    showdialog.value = false
                },
                )
            }
        }
    }
}
@Composable
fun ShowToast(context: Context,showIt:MutableState<Boolean>){
    if(showIt.value){
        Toast.makeText(context," Added to Favorites",Toast.LENGTH_SHORT).show()
    }
}
