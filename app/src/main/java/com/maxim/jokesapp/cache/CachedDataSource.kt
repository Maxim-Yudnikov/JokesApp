package com.maxim.jokesapp.cache

import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeServerModel

interface CachedDataSource {
    fun getJoke(jokeCachedCallback: JokeCachedCallback)
    fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}

interface JokeCachedCallback {
    fun provide(joke: Joke)
    fun fail()
}

