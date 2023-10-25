package com.maxim.jokesapp.data

interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}