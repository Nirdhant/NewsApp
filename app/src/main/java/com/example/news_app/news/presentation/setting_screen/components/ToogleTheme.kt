package com.example.NewsApp.news.presentation.setting_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.NewsApp.R
import com.example.tooglebutton.utils.Theme


@Composable
fun Toogle(){
    Column {
        Theme(
            height = 100.dp,
            lightIcon = painterResource(R.drawable.brightness),
            darkIcon = painterResource(R.drawable.dark_mode),
            isDark = false,
            backgroundColorDark = Color.White,
            backgroundColorLight = Color.Black,
            toogleColorDark = Color.Red,
            toogleColorLight = Color.Yellow,
            darkBorderColor = Color.Magenta,
            lightBorderColor = Color.Black,
            iconTintDark = Color.Black,
            iconTintLight = Color.White
        ) { }
    }
}

