package com.maxim.jokesapp

import com.maxim.jokesapp.cache.CacheDataSource
import com.maxim.jokesapp.cloud.CloudDataSource
import com.maxim.jokesapp.cloud.ErrorType
import com.maxim.jokesapp.joke.CachedJoke
import com.maxim.jokesapp.joke.FailedJokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeDataFetcher
import com.maxim.jokesapp.joke.JokeServerModel
import com.maxim.jokesapp.joke.JokeUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cacheResultHandler: CacheResultHandler,
    private val cloudResultHandler: CloudResultHandler,
    private val cachedJoke: CachedJoke
) : Model {
    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler

    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        return@withContext currentResultHandler.process()
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke?.change(cacheDataSource)

    override fun chooseDataSource(cached: Boolean) {
        currentResultHandler = if (cached) cacheResultHandler else cloudResultHandler
    }
}