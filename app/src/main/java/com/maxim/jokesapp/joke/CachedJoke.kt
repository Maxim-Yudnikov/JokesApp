package com.maxim.jokesapp.joke

import com.maxim.jokesapp.cache.ChangeJokeStatus

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus) : JokeUiModel?
}