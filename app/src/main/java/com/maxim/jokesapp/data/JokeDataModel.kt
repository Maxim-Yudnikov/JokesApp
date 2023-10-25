package com.maxim.jokesapp.data

import com.maxim.jokesapp.data.cache.ChangeJokeStatus
import com.maxim.jokesapp.domain.Joke

class JokeDataModel(
    private val id: Int,
    private val text: String,
    private val punchline: String,
    private val cached: Boolean = false
) : ChangeJoke {

    override suspend fun change(changeJokeStatus: ChangeJokeStatus) =
        changeJokeStatus.addOrRemove(id, this)

    fun <T> map(mapper: JokeDataModelMapper<T>): T {
        return mapper.map(id, text, punchline, cached)
    }

    fun changeCached(cached: Boolean) : JokeDataModel {
        return JokeDataModel(id, text, punchline, cached)
    }
}