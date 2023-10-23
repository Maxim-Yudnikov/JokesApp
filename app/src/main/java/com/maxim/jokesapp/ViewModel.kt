package com.maxim.jokesapp

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.joke.JokeUiModel

class ViewModel(private val model: Model) {
    private var dataCallback: DataCallback? = null

    private val jokeCallback = object : JokeCallback {
        override fun provide(joke: JokeUiModel) {
            dataCallback?.let {
                joke.map(it)
            }
        }
    }

    fun init(callback: DataCallback) {
        dataCallback = callback
        model.init(jokeCallback)
    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }
}


interface DataCallback {
    fun provideText(test: String)
    fun provideIconRes(@DrawableRes id: Int)
}