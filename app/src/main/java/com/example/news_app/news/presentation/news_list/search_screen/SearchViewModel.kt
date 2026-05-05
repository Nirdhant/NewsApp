package com.example.news_app.news.presentation.news_list.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.core.domain.util.onError
import com.example.news_app.core.domain.util.onSuccess
import com.example.news_app.news.data.networking.RemoteNewsDataSource
import com.example.news_app.news.presentation.news_list.model.toNewsUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val newsDataSource: RemoteNewsDataSource): ViewModel() {
    private val _searchState = MutableStateFlow(SearchState())
    val searchState : StateFlow<SearchState> = _searchState

    private val _searchEvents = Channel<SearchEvent>()
    val events = _searchEvents.receiveAsFlow()

    fun search(query:String){
        _searchState.update { it.copy(isLoading = true,searchQuery = query) }
        viewModelScope.launch{
            newsDataSource.searchNews(_searchState.value.searchQuery)
                .onSuccess {news->
                    _searchState.update { it.copy(isLoading = false,news = news.map { it.toNewsUI() }) }
                }
                .onError {error->
                    _searchState.update { it.copy(isLoading = false) }
                    _searchEvents.send(SearchEvent.Errors(error))
                }
        }
    }
}