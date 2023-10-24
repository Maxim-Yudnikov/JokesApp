package com.maxim.jokesapp

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.joke.JokeUiModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model): ViewModel() {
    private var dataCallback: DataCallback? = null

    fun init(callback: DataCallback) {
        dataCallback = callback
    }

    fun changeJokeStatus() = viewModelScope.launch {
        val uiModel = model.changeJokeStatus()
        dataCallback?.let {
            uiModel?.map(it)
        }
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun getJoke() = viewModelScope.launch {
        val uiModel = model.getJoke()
        dataCallback?.let {
            uiModel.map(it)
        }
    }

    fun clear() {
        dataCallback = null
    }
}


interface DataCallback {
    fun provideText(test: String)
    fun provideIconRes(@DrawableRes id: Int)
}