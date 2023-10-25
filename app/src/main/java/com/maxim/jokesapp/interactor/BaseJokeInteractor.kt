package com.maxim.jokesapp.interactor

import com.maxim.jokesapp.GenericError
import com.maxim.jokesapp.JokeFailure
import com.maxim.jokesapp.NoCachedJokes
import com.maxim.jokesapp.NoConnection
import com.maxim.jokesapp.ServiceUnavailable
import com.maxim.jokesapp.data.JokeDataModelMapper
import com.maxim.jokesapp.data.JokeRepository
import com.maxim.jokesapp.domain.Joke
import com.maxim.jokesapp.domain.NoCachedJokesException
import com.maxim.jokesapp.domain.NoConnectionException
import com.maxim.jokesapp.domain.ServiceUnavailableException

class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
): JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            repository.getJoke().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): Joke {
        return try {
            repository.changeJokeStatus().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun getFavoritesJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}



interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}
class JokeFailureFactory() : JokeFailureHandler {
    override fun handle(e: Exception): JokeFailure {
        return when(e){
            is NoConnectionException -> NoConnection()
            is NoCachedJokesException -> NoCachedJokes()
            is ServiceUnavailableException -> ServiceUnavailable()
            else -> GenericError()
        }
    }
}