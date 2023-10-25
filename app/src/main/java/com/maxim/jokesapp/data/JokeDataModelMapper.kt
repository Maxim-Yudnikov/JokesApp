package com.maxim.jokesapp.data

import com.maxim.jokesapp.domain.Joke

interface JokeDataModelMapper<T> {
    fun map(id: Int, text: String, punchline: String, cached: Boolean): T
}

class JokeSuccessMapper : JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean) =
        Joke.Success(text, punchline, cached)
}

class JokeRealmMapper : JokeDataModelMapper<JokeRealm> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.text = text
            it.punchline = punchline
        }
    }
}