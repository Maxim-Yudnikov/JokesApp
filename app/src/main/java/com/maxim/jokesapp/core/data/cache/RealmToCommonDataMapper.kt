package com.maxim.jokesapp.core.data.cache

import com.maxim.jokesapp.data.CommonDataModel
import com.maxim.jokesapp.data.cache.JokeRealm
import com.maxim.jokesapp.data.cache.QuoteRealmModel
import io.realm.RealmObject

interface RealmToCommonDataMapper<T: RealmObject, E> {
    fun map(realmObject: T): CommonDataModel<E>
}