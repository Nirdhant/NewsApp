package com.example.news_app.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.news_app.core.presentation.ObserveAsEvents
import com.example.news_app.core.presentation.toMessageString
import com.example.news_app.news.presentation.news_detail.DetailScreen
import com.example.news_app.news.presentation.news_list.home_screen.NewsListEvent
import com.example.news_app.news.presentation.news_list.home_screen.NewsViewModel
import com.example.news_app.news.presentation.news_list.home_screen.component.BottomNavigation
import com.example.news_app.news.presentation.news_list.home_screen.component.HomeLazyColumn
import com.example.news_app.news.presentation.news_list.model.NewsUI
import com.example.news_app.news.presentation.news_list.search_screen.SearchEvent
import com.example.news_app.news.presentation.news_list.search_screen.SearchScreen
import com.example.news_app.news.presentation.news_list.search_screen.SearchViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(viewModel:NewsViewModel=koinViewModel(),searchViewModel: SearchViewModel=koinViewModel()){

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

    NavHost(navController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute>{
            HomeLazyColumn(state = state.value,viewModel, onNewsClick = { news->
                navController.navigate(DetailScreenRoute(news))
            })
        }
        composable<SearchScreenRoute>{
            SearchScreen(searchState = searchState.value,searchViewModel=searchViewModel, onNewsClick = {news->
                navController.navigate(DetailScreenRoute(news))
            })
        }
        composable<SettingScreenRoute>{

        }
        composable<DetailScreenRoute>(typeMap = mapOf(typeOf<NewsUI>() to CustomNavType.news)){
            val args = it.toRoute<DetailScreenRoute>()
            DetailScreen(args.news)
        }
    }
    BottomNavigation(state,navController)

}


@Serializable
object HomeScreenRoute
@Serializable
object SearchScreenRoute
@Serializable
object SettingScreenRoute
@Serializable
data class DetailScreenRoute(val news: NewsUI)