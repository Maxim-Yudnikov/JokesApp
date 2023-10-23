package com.maxim.jokesapp.joke

import com.maxim.jokesapp.cache.CachedDataSource

class Joke(
    private val id: Int,
    private val type: String,
    private val text: String,
    private val punchline: String
) {
    fun change(cachedDataSource: CachedDataSource) = cachedDataSource.addOrRemove(id, this)
    fun toBaseJoke() = BaseJokeUiModel(text, punchline)
    fun toFavoriteJoke() = FavoriteJokeUiModel(text, punchline)
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type
            it.text = text
            it.punchline = punchline
        }
    }
}