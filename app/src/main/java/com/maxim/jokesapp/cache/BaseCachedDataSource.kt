package com.maxim.jokesapp.cache

import android.util.Log
import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeRealm
import io.realm.Realm
import com.maxim.jokesapp.Result
import com.maxim.jokesapp.cloud.RealmProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(private val realmProvider: RealmProvider) : CacheDataSource {
    override suspend fun getJoke(): Result<Joke, Unit> {
        realmProvider.provide().let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                return Result.Error(Unit)
            else {
                jokes.forEach {
                    Log.d("MyLog", "Random joke from favorites ids: ${it.id}")
                }
                jokes.random().let {
                    return Result.Success(
                        Joke(
                            it.id,
                            it.type,
                            it.text,
                            it.punchline
                        )
                    )
                }
            }
        }
    }

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel =
        withContext(Dispatchers.IO) {
            Realm.getDefaultInstance().use {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return@withContext if (jokeRealm == null) {
                    it.executeTransaction {
                        val newJoke = joke.toJokeRealm()
                        it.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.toBaseJoke()
                }
            }
        }
}