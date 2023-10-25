package com.maxim.jokesapp.data

import com.maxim.jokesapp.data.cache.CacheDataSource
import com.maxim.jokesapp.data.cloud.CloudDataSource
import com.maxim.jokesapp.joke.JokeUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
) : JokeRepository {
    private var currentDataSource: JokeDataFetcher = cloudDataSource

    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO) {
        try {
            val joke = currentDataSource.getJoke()
            cachedJoke.saveJoke(joke)
            return@withContext joke
        } catch (e: Exception) {
            cachedJoke.clear()
            throw e
        }
    }

    override suspend fun changeJokeStatus(): JokeDataModel = cachedJoke.change(cacheDataSource)

    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }
}