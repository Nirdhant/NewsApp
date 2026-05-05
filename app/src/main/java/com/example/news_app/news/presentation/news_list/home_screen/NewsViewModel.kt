package com.example.news_app.news.presentation.news_list.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.core.domain.util.onError
import com.example.news_app.core.domain.util.onSuccess
import com.example.news_app.news.domain.NewsDataSource
import com.example.news_app.news.presentation.news_list.model.toNewsUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel(private val newsDataSource: NewsDataSource): ViewModel() {
    private val _state = MutableStateFlow(NewsState())
    val state = _state.onStart { loadNews() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), NewsState())

    private val _events = Channel<NewsListEvent>()
    val events = _events.receiveAsFlow()
    //------------------------------------------------------------------------------------


    fun onAction(action: HomeScreenAction){
        when(action){
            is HomeScreenAction.OnNewsClick->{
            }
        }
    }

     fun loadNews(){
         _state.update { it.copy(isLoading = true) }
         viewModelScope.launch{
             newsDataSource.getNews()
                .onSuccess {news->
                    _state.update { it.copy(isLoading = false,news = news.map { it.toNewsUI() }) }
                }
                .onError { error->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(NewsListEvent.Errors(error))
                }
        }

    }
    fun onDelete(index: Int){
        val updatedNews = _state.value.news.toMutableList().apply {
            removeAt(index)           //index by removed by id so error may occur
        }
        _state.value = _state.value.copy(news=updatedNews)
    }
}


