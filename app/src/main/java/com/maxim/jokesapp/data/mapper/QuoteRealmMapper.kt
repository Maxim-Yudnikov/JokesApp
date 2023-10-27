package com.maxim.jokesapp.data.mapper

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.data.cache.QuoteRealmModel

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealmModel, String> {
    override fun map(id: String, first: String, second: String, cached: Boolean): QuoteRealmModel {
        return QuoteRealmModel().also {
            it.id = id
            it.content = first
            it.author = second
        }
    }
}