package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.data.mapper.QuoteRealmMapper
import io.realm.Realm

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    realmToCommonMapper: QuoteRealmToCommonDataMapper,
    mapper: QuoteRealmMapper
) : BaseCacheDataSource<QuoteRealmModel, String>(realmProvider, realmToCommonMapper, mapper) {
    override val dbClass = QuoteRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: String): QuoteRealmModel? =
        realm.where(dbClass).equalTo("id", id).findFirst()
}