package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.data.JokeDataFetcher
import com.maxim.jokesapp.data.JokeDataModel

interface CacheDataSource : ChangeJokeStatus, JokeDataFetcher

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel
}

