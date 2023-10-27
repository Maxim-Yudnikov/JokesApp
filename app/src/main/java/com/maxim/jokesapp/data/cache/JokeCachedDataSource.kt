package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.data.mapper.JokeRealmMapper
import io.realm.Realm

class JokeCachedDataSource(
    realmProvider: RealmProvider,
    realmToCommonMapper: JokeRealmToCommonDataMapper,
    mapper: JokeRealmMapper
) : BaseCacheDataSource<JokeRealm, Int>(realmProvider, realmToCommonMapper, mapper) {
    override val dbClass = JokeRealm::class.java
    override fun findRealmObject(realm: Realm, id: Int): JokeRealm? =
        realm.where(dbClass).equalTo("id", id).findFirst()
}