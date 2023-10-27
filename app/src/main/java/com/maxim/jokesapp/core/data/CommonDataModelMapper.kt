package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.cache.JokeRealm
import com.maxim.jokesapp.data.cache.QuoteRealmModel
import com.maxim.jokesapp.domain.CommonItem

interface CommonDataModelMapper<T, E> {
    fun map(id: E, first: String, second: String, cached: Boolean): T
}