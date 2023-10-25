package com.maxim.jokesapp.joke

import com.maxim.jokesapp.data.CachedJoke
import com.maxim.jokesapp.data.ChangeJoke
import com.maxim.jokesapp.data.JokeDataModel
import com.maxim.jokesapp.data.cache.ChangeJokeStatus
import com.maxim.jokesapp.domain.Joke

class BaseCachedJoke: CachedJoke {
    private var cached: ChangeJoke = ChangeJoke.Empty()
    override fun saveJoke(joke: JokeDataModel) {
        cached = joke
    }

    override fun clear() {
        cached = ChangeJoke.Empty()
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
        return cached.change(changeJokeStatus)
    }
}