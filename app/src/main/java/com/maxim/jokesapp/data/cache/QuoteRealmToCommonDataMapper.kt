package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmToCommonDataMapper
import com.maxim.jokesapp.data.CommonDataModel

class QuoteRealmToCommonDataMapper : RealmToCommonDataMapper<QuoteRealmModel, String> {
    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.id, realmObject.content, realmObject.author, true)
}