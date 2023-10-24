package com.maxim.jokesapp

import com.maxim.jokesapp.joke.JokeUiModel

interface Model {
    suspend fun getJoke(): JokeUiModel
    suspend fun changeJokeStatus(): JokeUiModel?
    fun chooseDataSource(cached: Boolean)
}