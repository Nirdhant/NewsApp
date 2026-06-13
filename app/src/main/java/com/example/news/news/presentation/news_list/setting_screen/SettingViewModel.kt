package com.example.news.news.presentation.news_list.setting_screen

import androidx.lifecycle.ViewModel
import com.example.news.core.presentation.theme.ThemeMode
import com.example.news.core.presentation.theme.ThemeRepository

class SettingViewModel(private val themeRepository: ThemeRepository): ViewModel() {

    val currentTheme = themeRepository.currentTheme

    fun onThemeToogle(isDark: Boolean){
        val newMode = if (isDark) ThemeMode.DARK else ThemeMode.LIGHT
        themeRepository.changeTheme(newMode)
    }
}