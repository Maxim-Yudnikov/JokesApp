package com.maxim.jokesapp

import com.maxim.jokesapp.cloud.ErrorType
import com.maxim.jokesapp.joke.CachedJoke
import com.maxim.jokesapp.joke.FailedJokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeDataFetcher
import com.maxim.jokesapp.joke.JokeServerModel
import com.maxim.jokesapp.joke.JokeUiModel

abstract class BaseResultHandler<S, E>(private val jokeDataFetcher: JokeDataFetcher<S, E>) {
    suspend fun process(): JokeUiModel {
        return handleResult(jokeDataFetcher.getJoke())
    }

    protected abstract fun handleResult(result: Result<S, E>): JokeUiModel
}

class CloudResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
    private val noConnection: JokeFailure,
    private val serviceUnavailable: JokeFailure
) : BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher) {
    override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUiModel =
        when (result) {
            is Result.Success<JokeServerModel> -> {
                result.data.toJoke().let {
                    cachedJoke.saveJoke(it)
                    it.toBaseJoke()
                }
            }

            is Result.Error<ErrorType> -> {
                cachedJoke.clear()
                val failure =
                    if (result.exception == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                FailedJokeUiModel(failure.getMessage())
            }
        }
}

class CacheResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
    private val noCachedJokes: JokeFailure
) :
    BaseResultHandler<Joke, Unit>(jokeDataFetcher) {
    override fun handleResult(result: Result<Joke, Unit>): JokeUiModel = when (result) {
        is Result.Success<Joke> -> result.data.let {
            cachedJoke.saveJoke(it)
            it.toFavoriteJoke()
        }

        is Result.Error -> {
            cachedJoke.clear()
            FailedJokeUiModel(noCachedJokes.getMessage())
        }
    }
}