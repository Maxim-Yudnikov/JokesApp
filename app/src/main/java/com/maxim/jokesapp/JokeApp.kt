package  com.maxim.jokesapp

import android.app.Application
import com.maxim.jokesapp.cache.BaseCacheDataSource
import com.maxim.jokesapp.cloud.BaseCloudDataSource
import com.maxim.jokesapp.cloud.BaseRealmProvider
import com.maxim.jokesapp.joke.BaseCachedJoke
import com.maxim.jokesapp.joke.JokeService
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class JokeApp : Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        Realm.init(this)

        val cachedJoke = BaseCachedJoke()
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider())

        viewModel = ViewModel(
            BaseModel(
                cacheDataSource,
                CacheResultHandler(cachedJoke, cacheDataSource, NoCachedJokes()),
                CloudResultHandler(
                    cachedJoke,
                    BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                    NoConnection(),
                    ServiceUnavailable()
                ),
                cachedJoke
            )
        )
    }
}