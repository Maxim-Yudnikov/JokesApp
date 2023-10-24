package com.maxim.jokesapp

import com.maxim.jokesapp.cache.CacheDataSource
import com.maxim.jokesapp.cloud.CloudDataSource
import com.maxim.jokesapp.cloud.ErrorType
import com.maxim.jokesapp.joke.FailedJokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeServerModel
import com.maxim.jokesapp.joke.JokeUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource
) : Model {
    private val noConnection by lazy { NoConnection() }
    private val serviceUnavailable by lazy { ServiceUnavailable() }
    private val noCachedJokes by lazy { NoCachedJokes() }

    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false
    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        if (getJokeFromCache) {
            return@withContext when (val result = cacheDataSource.getJoke()) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    FailedJokeUiModel(noCachedJokes.getMessage())
                }
            }
        } else {
            return@withContext when (val result = cloudDataSource.getJoke()) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJoke().let {
                        cachedJoke = it
                        it.toBaseJoke()
                    }
                }

                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    val failure =
                        if (result.exception == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    FailedJokeUiModel(failure.getMessage())
                }
            }
        }
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke?.change(cacheDataSource)

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}