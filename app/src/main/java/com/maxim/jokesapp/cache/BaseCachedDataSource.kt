package com.maxim.jokesapp.cache

import android.util.Log
import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeRealm
import com.maxim.jokesapp.joke.JokeServerModel
import io.realm.Realm

class BaseCachedDataSource(private val realm: Realm) : CachedDataSource {
    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                jokeCachedCallback.fail()
            else {
                jokes.forEach {
                    Log.d("MyLog", "Random joke from favorites ids: ${it.id}")
                }
                jokes.random().let {
                    jokeCachedCallback.provide(
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

    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
            return if (jokeRealm == null) {
                val newJoke = joke.toJokeRealm()
                it.executeTransaction { transaction ->
                    transaction.insert(newJoke)
                    Log.d("MyLog", "id: ${newJoke.id}")
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