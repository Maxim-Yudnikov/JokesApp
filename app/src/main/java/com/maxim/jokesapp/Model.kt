package com.maxim.jokesapp

import com.maxim.jokesapp.joke.JokeUiModel

interface Model {
    fun getJoke()
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(jokeCallback: JokeCallback)
    fun chooseDataSource(cached: Boolean)
}

interface JokeCallback {
    fun provide(joke: JokeUiModel)
}