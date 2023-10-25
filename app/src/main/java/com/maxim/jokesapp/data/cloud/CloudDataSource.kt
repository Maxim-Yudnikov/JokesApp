package com.maxim.jokesapp.data.cloud

import com.maxim.jokesapp.data.JokeDataFetcher
import com.maxim.jokesapp.data.JokeServerModel

interface CloudDataSource : JokeDataFetcher

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}