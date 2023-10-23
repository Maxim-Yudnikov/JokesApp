package com.maxim.jokesapp.cloud

import com.maxim.jokesapp.joke.Joke

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}