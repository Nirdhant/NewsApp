package com.example.news_app.news.presentation.home_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val badge:Boolean=false,
    val badgeCount:Int?=null
)

val bottomNavigationItemList= listOf(
    BottomNavigationItem("Home", Icons.Filled.Home,Icons.Outlined.Home),
    BottomNavigationItem("Search", Icons.Filled.Search,Icons.Outlined.Search, badgeCount = 100),
    BottomNavigationItem("Settings", Icons.Filled.Settings,Icons.Outlined.Settings ,badge = true)
)



