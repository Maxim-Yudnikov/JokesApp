package com.maxim.jokesapp.joke

import com.maxim.jokesapp.cache.CacheDataSource
import com.maxim.jokesapp.cache.ChangeJokeStatus

class Joke(
    private val id: Int,
    private val type: String,
    private val text: String,
    private val punchline: String
) : ChangeJoke {
    override suspend fun change(cachedDataSource: ChangeJokeStatus) = cachedDataSource.addOrRemove(id, this)
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