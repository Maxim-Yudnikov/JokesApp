package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.data.mapper.JokeRealmMapper

class JokeCachedDataSource(
    realmProvider: RealmProvider,
    realmToCommonMapper: JokeRealmToCommonDataMapper,
    mapper: JokeRealmMapper
) :
    BaseCacheDataSource<JokeRealm>(realmProvider, realmToCommonMapper, mapper) {
    override val dbClass = JokeRealm::class.java
}