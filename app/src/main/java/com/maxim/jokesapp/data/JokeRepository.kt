package com.maxim.jokesapp.data

interface JokeRepository {
    suspend fun getJoke(): JokeDataModel
    suspend fun changeJokeStatus(): JokeDataModel
    fun chooseDataSource(cached: Boolean)
}