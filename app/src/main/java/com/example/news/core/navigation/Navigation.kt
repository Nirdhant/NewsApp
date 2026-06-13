package com.example.news.core.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.news.core.presentation.ObserveAsEvents
import com.example.news.core.presentation.toMessageString
import com.example.news.news.presentation.news_detail.DetailScreen
import com.example.news.news.presentation.news_list.home_screen.NewsListEvent
import com.example.news.news.presentation.news_list.home_screen.NewsViewModel
import com.example.news.news.presentation.news_list.home_screen.component.BottomNavigation
import com.example.news.news.presentation.news_list.home_screen.component.HomeLazyColumn
import com.example.news.news.presentation.news_list.model.NewsUI
import com.example.news.news.presentation.news_list.search_screen.SearchEvent
import com.example.news.news.presentation.news_list.search_screen.SearchScreen
import com.example.news.news.presentation.news_list.search_screen.SearchViewModel
import com.example.news.news.presentation.news_list.setting_screen.SettingScreen
import com.example.news.news.presentation.news_list.setting_screen.SettingViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(
    viewModel:NewsViewModel=koinViewModel(),
    searchViewModel: SearchViewModel=koinViewModel(),
    settingViewModel: SettingViewModel=koinViewModel()
){

    val state = viewModel.state.collectAsStateWithLifecycle()
    val searchState = searchViewModel.searchState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    ObserveAsEvents(events = viewModel.events) {events->
        when(events){
            is NewsListEvent.Errors->{
                Toast.makeText(context,events.error.toMessageString(context),Toast.LENGTH_LONG).show()
            }
        }
    }
    ObserveAsEvents(events = searchViewModel.events) {events->
        when(events){
            is SearchEvent.Errors->{
                Toast.makeText(context,events.error.toMessageString(context),Toast.LENGTH_LONG).show()
            }
        }
    }

    val navController = rememberNavController()
    val isUIVisible = remember { mutableStateOf(true) }
    val nestedScroll = remember {
        object : NestedScrollConnection{
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                if (available.y <-15f){
//                    isUIVisible.value=false
//                }
//                else if(available.y > 15f){
//                    isUIVisible.value = true
//                }
//                return Offset.Zero
//            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (consumed.y < -3f) {
                    isUIVisible.value = false
                } else if (consumed.y > 3f) {
                    isUIVisible.value = true
                }
                return Offset.Zero
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().nestedScroll(nestedScroll)) {
        NavHost(navController, startDestination = HomeScreenRoute) {
            composable<HomeScreenRoute> {
                HomeLazyColumn(state = state.value, viewModel, onNewsClick = { news ->
                    navController.navigate(DetailScreenRoute(news)){}
                })
            }
            composable<SearchScreenRoute> {
                SearchScreen(
                    searchState = searchState.value,
                    searchViewModel = searchViewModel,
                    isUIVisible = isUIVisible.value,
                    onNewsClick = { news ->
                        navController.navigate(DetailScreenRoute(news))
                    })
            }
            composable<SettingScreenRoute> {
                SettingScreen(settingViewModel)

            }
            composable<DetailScreenRoute>(typeMap = mapOf(typeOf<NewsUI>() to CustomNavType.news)) {
                val args = it.toRoute<DetailScreenRoute>()
                DetailScreen(args.news)
            }
        }
        BottomNavigation(state, navController,isUIVisible.value)
    }
}


@Serializable
object HomeScreenRoute
@Serializable
object SearchScreenRoute
@Serializable
object SettingScreenRoute
@Serializable
data class DetailScreenRoute(val news: NewsUI)