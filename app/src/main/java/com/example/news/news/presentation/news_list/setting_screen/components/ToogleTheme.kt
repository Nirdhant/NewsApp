package com.example.news.news.presentation.news_list.setting_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.tooglebutton.utils.Theme


@Composable
fun Toogle(isDarkMode: Boolean, onClick:()-> Unit){
    Column {
        Theme(
            height = 100.dp,
            lightIcon = painterResource(R.drawable.brightness),
            darkIcon = painterResource(R.drawable.dark_mode),
            isDark = isDarkMode,
            backgroundColorDark = MaterialTheme.colorScheme.surfaceContainerHigh,
            backgroundColorLight = MaterialTheme.colorScheme.surfaceContainerHigh,
            toogleColorDark = MaterialTheme.colorScheme.primary,
            toogleColorLight = MaterialTheme.colorScheme.primary,
            darkBorderColor = MaterialTheme.colorScheme.outline,
            lightBorderColor = MaterialTheme.colorScheme.outline,
            iconTintDark = MaterialTheme.colorScheme.onPrimary,
            iconTintLight = MaterialTheme.colorScheme.onPrimary
        ) {
             onClick()
        }
    }
}

