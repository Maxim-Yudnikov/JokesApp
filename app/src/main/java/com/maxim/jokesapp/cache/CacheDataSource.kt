package com.maxim.jokesapp.cache

import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.Result

interface CacheDataSource {
    suspend fun getJoke(): Result<Joke, Unit>
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}

