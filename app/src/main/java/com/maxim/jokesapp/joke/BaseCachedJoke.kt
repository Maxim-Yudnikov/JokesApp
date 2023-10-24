package com.maxim.jokesapp.joke

import com.maxim.jokesapp.cache.ChangeJokeStatus

class BaseCachedJoke: CachedJoke {
    private var cached: Joke? = null
    override fun saveJoke(joke: Joke) {
        cached = joke
    }

    override fun clear() {
        cached = null
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUiModel? {
        return cached?.change(changeJokeStatus)
    }
}