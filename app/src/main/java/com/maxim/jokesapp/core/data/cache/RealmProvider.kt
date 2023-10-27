package com.maxim.jokesapp.core.data.cache

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}