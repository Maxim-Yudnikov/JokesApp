package com.maxim.jokesapp.data.mapper

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.data.cache.JokeRealm

class JokeRealmMapper : CommonDataModelMapper<JokeRealm> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.text = first
            it.punchline = second
        }
    }
}