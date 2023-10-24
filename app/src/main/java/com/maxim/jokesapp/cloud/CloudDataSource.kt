package com.maxim.jokesapp.cloud

import com.maxim.jokesapp.Result
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeServerModel

interface CloudDataSource {
    suspend fun getJoke(): Result<JokeServerModel, ErrorType>
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}