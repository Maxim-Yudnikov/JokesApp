package com.maxim.jokesapp.cache

import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.Result
import com.maxim.jokesapp.joke.JokeDataFetcher

interface CacheDataSource : ChangeJokeStatus, JokeDataFetcher<Joke, Unit>

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}

