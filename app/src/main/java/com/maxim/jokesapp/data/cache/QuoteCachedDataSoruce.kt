package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.data.mapper.QuoteRealmMapper

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    realmToCommonMapper: QuoteRealmToCommonDataMapper,
    mapper: QuoteRealmMapper
) :
    BaseCacheDataSource<QuoteRealmModel>(realmProvider, realmToCommonMapper, mapper) {
    override val dbClass = QuoteRealmModel::class.java
}