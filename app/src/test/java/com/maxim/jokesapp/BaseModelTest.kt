package com.maxim.jokesapp

import com.maxim.jokesapp.cache.CacheDataSource
import com.maxim.jokesapp.cloud.CloudDataSource
import com.maxim.jokesapp.cloud.ErrorType
import com.maxim.jokesapp.joke.BaseCachedJoke
import com.maxim.jokesapp.joke.BaseJokeUiModel
import com.maxim.jokesapp.joke.CachedJoke
import com.maxim.jokesapp.joke.Joke
import com.maxim.jokesapp.joke.JokeServerModel
import com.maxim.jokesapp.joke.JokeUiModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseModelTest {
    @Test
    fun test_change_data_source(): Unit = runBlocking {
        val cacheDataSource = FakeCacheDataSource()
        val cloudDataSource = FakeCloudDataSource()
        val cachedJoke = BaseCachedJoke()
        val cacheResultHandler = CacheResultHandler(cachedJoke, cacheDataSource, NoCachedJokes())
        val cloudResultHandler = CloudResultHandler(cachedJoke, FakeCloudDataSource(), NoConnection(), ServiceUnavailable())
        val model = BaseModel(cacheDataSource, cacheResultHandler, cloudResultHandler, cachedJoke)

        model.chooseDataSource(false)
        cloudDataSource.getJokeWithResult(true)
        val joke = model.getJoke()
        assertEquals(joke is BaseJokeUiModel, true)
        model.changeJokeStatus()
        assertEquals(cacheDataSource.checkContainsId(0), true)
    }


    private inner class FakeCacheDataSource() : CacheDataSource {
        private val map = HashMap<Int, Joke>()
        private var success = true
        private var nextJokeIdToGet = -1

        fun getNextJokeWithResult(success: Boolean, id: Int) {
            this.success = success
            nextJokeIdToGet = id
        }

        override suspend fun getJoke(): Result<Joke, Unit> {
            return if (success) {
                Result.Success(map[nextJokeIdToGet]!!)
            } else {
                Result.Error(Unit)
            }
        }

        override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
            return if (map.containsKey(id)) {
                val result = map[id]!!.toBaseJoke()
                map.remove(id)
                result
            } else {
                map[id] = joke
                joke.toFavoriteJoke()
            }
        }

        fun checkContainsId(id: Int) = map.containsKey(id)
    }

    private inner class FakeCloudDataSource() : CloudDataSource {
        private var success = true
        private var count = 0

        fun getJokeWithResult(success: Boolean) {
            this.success = success
        }

        override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
            return if (success) {
                Result.Success(JokeServerModel(count++, "type", "text$count", "punchline$count"))
            } else {
                Result.Error(ErrorType.NO_CONNECTION)
            }
        }

    }
}