package com.maxim.jokesapp.data.cache

import android.content.Context
import com.maxim.jokesapp.core.data.cache.RealmProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class BaseRealmProvider(context: Context, useMocks: Boolean) : RealmProvider {
    init {
        Realm.init(context)
        val fileName = if(useMocks) MOCKS else NAME
        val config = RealmConfiguration.Builder().name(fileName).build()
        Realm.setDefaultConfiguration(config)
    }
    override fun provide(): Realm = Realm.getDefaultInstance()

    companion object {
        const val NAME = "jokesAndQuotesRealm"
        const val MOCKS = "jokesAndQuotesRealmMocks"
    }
}