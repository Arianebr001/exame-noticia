package com.news.noticiasapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.noticiasapp.api.RetrofitInstance
import com.news.noticiasapp.models.News
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _newsList = mutableStateOf<List<News>>(emptyList())
    val newsList: State<List<News>> = _newsList

    private val _currentNews = mutableStateOf<News?>(null)
    val currentNews: State<News?> = _currentNews

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        fetchAllNews()
    }

    fun fetchAllNews() {
        viewModelScope.launch {
            try {
                _newsList.value = RetrofitInstance.api.getAllNews()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun fetchNewsById(id: Int) {
        viewModelScope.launch {
            try {
                _currentNews.value = RetrofitInstance.api.getNewsById(id)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun createNews(news: News) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.createNews(news)
                fetchAllNews()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateNews(id: Int, news: News) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updateNews(id, news)
                fetchAllNews()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteNews(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteNews(id)
                fetchAllNews()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
