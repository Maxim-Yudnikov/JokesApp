package com.maxim.jokesapp.data

import com.maxim.jokesapp.data.cache.ChangeJokeStatus
import java.lang.IllegalStateException

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: JokeDataModel)
    fun clear()
}

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus) : JokeDataModel

    class Empty() : ChangeJoke {
        override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
            throw IllegalStateException("empty change joke called")
        }
    }
}