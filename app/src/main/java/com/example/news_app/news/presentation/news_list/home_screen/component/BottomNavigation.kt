package com.example.news_app.news.presentation.news_list.home_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.news_app.core.navigation.HomeScreenRoute
import com.example.news_app.core.navigation.SearchScreenRoute
import com.example.news_app.core.navigation.SettingScreenRoute
import com.example.news_app.news.presentation.news_list.home_screen.NewsState

@Composable
fun BottomNavigation(
    state: State<NewsState>,
    navController: NavHostController
) {
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    //this is default selected index for my list of bottomNavigationItems
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            //-------------------------------Bottom Navigation-------------------------------------------------------------------------------------------------------------------------------------------
            NavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().shadow(
                    elevation = 50.dp,
                    ambientColor = Color.Yellow,
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                ).height(60.dp)
            )
            {
                bottomNavigationItemList.forEachIndexed { index, eachItem ->
                    NavigationBarItem(
                        modifier = Modifier.size(25.dp),
                        selected = selectedItemIndex == index,
                        label = { Text(text = eachItem.title) },
                        icon = {
                            BadgedBox(badge = {
                                if (eachItem.badge) Badge()
                                else if (eachItem.badgeCount != null) Badge { Text(text = eachItem.badgeCount.toString()) }
                            }
                            ) {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) eachItem.selectedIcon else eachItem.unselectedIcon,
                                    contentDescription = eachItem.title
                                )
                            }
                        },
                        onClick = {
                            selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(HomeScreenRoute)
                                1 -> navController.navigate(SearchScreenRoute)
                                2 -> navController.navigate(SettingScreenRoute)
                            }
                        }
                    )
                }
            }
        }
    }
}