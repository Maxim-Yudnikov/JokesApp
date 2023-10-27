package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.RealmProvider
import io.realm.Realm

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}