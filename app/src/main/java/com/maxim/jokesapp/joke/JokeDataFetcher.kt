package com.maxim.jokesapp.joke

import com.maxim.jokesapp.Result

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}