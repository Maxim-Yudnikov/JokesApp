package com.maxim.jokesapp.data.cache

import android.util.Log
import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.domain.Joke
import com.maxim.jokesapp.data.JokeRealm
import io.realm.Realm
import com.maxim.jokesapp.data.JokeDataModel
import com.maxim.jokesapp.data.JokeDataModelMapper
import com.maxim.jokesapp.data.cloud.RealmProvider
import com.maxim.jokesapp.domain.NoCachedJokesException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCacheDataSource(
    private val realmProvider: RealmProvider,
    private val mapper: JokeDataModelMapper<JokeRealm>
) : CacheDataSource {
    override suspend fun getJoke(): JokeDataModel {
        realmProvider.provide().use {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                throw NoCachedJokesException()
            else {
                val jokeRealm = it.copyFromRealm(jokes.random())
                return jokeRealm.to() //toJokeDataModel
            }
        }
    }

    override suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return@withContext if (jokeRealm == null) {
                    it.executeTransaction { transaction ->
                        val newJoke = joke.map(mapper)
                        transaction.insert(newJoke)
                    }
                    joke.changeCached(true)
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.changeCached(false)
                }
            }
        }
}