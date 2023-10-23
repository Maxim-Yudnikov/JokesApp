package com.maxim.jokesapp

import com.maxim.jokesapp.cache.CachedDataSource
import com.maxim.jokesapp.cache.JokeCachedCallback
import com.maxim.jokesapp.cloud.CloudDataSource
import com.maxim.jokesapp.cloud.ErrorType
import com.maxim.jokesapp.cloud.JokeCloudCallback
import com.maxim.jokesapp.joke.FailedJokeUiModel
import com.maxim.jokesapp.joke.Joke

class BaseModel(
    private val cacheDataSource: CachedDataSource,
    private val cloudDataSource: CloudDataSource
) : Model {
    private val noConnection by lazy { NoConnection() }
    private val serviceUnavailable by lazy { ServiceUnavailable() }
    private val noCachedJokes by lazy { NoCachedJokes() }

    private var jokeCallback: JokeCallback? = null
    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false
    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    cachedJoke = null
                    jokeCallback?.provide(FailedJokeUiModel(noCachedJokes.getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJoke = null
                    val failure =
                        if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJokeUiModel(failure.getMessage()))
                }
            })
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJoke?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}