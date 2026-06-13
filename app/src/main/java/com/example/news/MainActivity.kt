package com.example.news
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.news.core.navigation.AppNavigation
import com.example.news.core.presentation.theme.ThemeMode
import com.example.news.core.presentation.theme.ThemeRepository
import com.example.news.news.presentation.ui.theme.NewsTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val themeRepository : ThemeRepository by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode = themeRepository.currentTheme.collectAsStateWithLifecycle()
            val isDarkMode = when (themeMode.value) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
            }
            NewsTheme(darkTheme = isDarkMode,dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}


