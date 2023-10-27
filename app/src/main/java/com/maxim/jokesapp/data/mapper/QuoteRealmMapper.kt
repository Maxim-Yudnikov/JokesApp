package com.maxim.jokesapp.data.mapper

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.data.cache.QuoteRealmModel

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealmModel> {
    override fun map(id: Int, first: String, second: String, cached: Boolean): QuoteRealmModel {
        return QuoteRealmModel().also {
            it.id = id
            it.content = first
            it.author = second
        }
    }
}