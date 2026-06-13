package com.example.news.news.presentation.news_list.setting_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.news.core.presentation.theme.ThemeMode
import com.example.news.news.presentation.news_list.setting_screen.components.Toogle


@Composable
fun SettingScreen(settingViewModel: SettingViewModel){
    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val isDark = settingViewModel.currentTheme.collectAsState()
        val isDarkMode = isDark.value == ThemeMode.DARK

        Toogle(isDarkMode) {
          //  isDarkMode = !isDarkMode
            settingViewModel.onThemeToogle(!isDarkMode)
        }

    }
}
