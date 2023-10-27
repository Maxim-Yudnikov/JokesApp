package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.cache.JokeRealm
import com.maxim.jokesapp.data.cache.QuoteRealmModel
import com.maxim.jokesapp.domain.CommonItem

interface CommonDataModelMapper<T> {
    fun map(id: Int, first: String, second: String, cached: Boolean): T
}