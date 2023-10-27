package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmToCommonDataMapper
import com.maxim.jokesapp.data.CommonDataModel

class JokeRealmToCommonDataMapper : RealmToCommonDataMapper<JokeRealm, Int> {
    override fun map(realmObject: JokeRealm) =
        CommonDataModel(realmObject.id, realmObject.text, realmObject.punchline, true)
}