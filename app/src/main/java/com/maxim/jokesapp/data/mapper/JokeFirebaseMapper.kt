package com.maxim.jokesapp.data.mapper

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.data.cache.JokeFirebaseModel

class JokeFirebaseMapper : CommonDataModelMapper<JokeFirebaseModel, Int> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): JokeFirebaseModel {
        return JokeFirebaseModel(System.currentTimeMillis().toString(), id.toString(), first, second)
    }
}