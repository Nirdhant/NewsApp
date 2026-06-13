package com.example.news.news.presentation.news_list.home_screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.news.core.navigation.HomeScreenRoute
import com.example.news.core.navigation.SearchScreenRoute
import com.example.news.core.navigation.SettingScreenRoute
import com.example.news.news.presentation.news_list.home_screen.NewsState

@Composable
fun BottomNavigation(
    state: State<NewsState>,
    navController: NavHostController,
    isUIVisible: Boolean
) {
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    //this is default selected index for my list of bottomNavigationItems
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            //-------------------------------Bottom Navigation-------------------------------------------------------------------------------------------------------------------------------------------
            AnimatedVisibility(
                visible = isUIVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().windowInsetsPadding(
                    WindowInsets.navigationBars)
            ) {
                NavigationBar(
                    modifier = Modifier
                        .shadow(
                            elevation = 50.dp,
                            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                        )
                        .height(70.dp),
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
                {
                    bottomNavigationItemList.forEachIndexed { index, eachItem ->
                        NavigationBarItem(
                            modifier = Modifier.size(25.dp),
                            selected = selectedItemIndex == index,
                            label = { Text(text = eachItem.title) },
                            icon = {
                                BadgedBox(badge = {
                                    if (eachItem.badge) Badge(
                                        containerColor = MaterialTheme.colorScheme.error,
                                        contentColor = MaterialTheme.colorScheme.onError
                                    )
                                    else if (eachItem.badgeCount != null) Badge(
                                        containerColor = MaterialTheme.colorScheme.error,
                                        contentColor = MaterialTheme.colorScheme.onError
                                    ) { Text(text = eachItem.badgeCount.toString()) }
                                }
                                ) {
                                    Icon(
                                        imageVector = if (selectedItemIndex == index) eachItem.selectedIcon else eachItem.unselectedIcon,
                                        contentDescription = eachItem.title
                                    )
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            onClick = {
                                selectedItemIndex = index
                                val route = when(index) {
                                    0 -> HomeScreenRoute
                                    1 -> SearchScreenRoute
                                    else -> SettingScreenRoute
                                }
                                navController.navigate(route){
                                   // popUpToRoute
                                    popUpTo(0)
                                    launchSingleTop=true
                                }
                            }
                        )
                    }
                }
            }
            //
        }
    }
}