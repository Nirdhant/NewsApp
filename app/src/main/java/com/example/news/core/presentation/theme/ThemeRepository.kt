package com.example.news.core.presentation.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ThemeMode { LIGHT, DARK }

class ThemeRepository {
    private val _currentTheme = MutableStateFlow(ThemeMode.LIGHT)
    val currentTheme: StateFlow<ThemeMode> = _currentTheme.asStateFlow()

    fun changeTheme(mode: ThemeMode) {
        _currentTheme.value = mode
    }
}
