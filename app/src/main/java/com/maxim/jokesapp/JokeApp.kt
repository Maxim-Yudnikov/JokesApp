package  com.maxim.jokesapp

import android.app.Application
import com.maxim.jokesapp.data.BaseJokeRepository
import com.maxim.jokesapp.data.JokeRealmMapper
import com.maxim.jokesapp.data.JokeServerModel
import com.maxim.jokesapp.data.JokeSuccessMapper
import com.maxim.jokesapp.data.cache.BaseCacheDataSource
import com.maxim.jokesapp.data.cloud.BaseCloudDataSource
import com.maxim.jokesapp.data.cloud.BaseRealmProvider
import com.maxim.jokesapp.interactor.BaseJokeInteractor
import com.maxim.jokesapp.interactor.JokeFailureFactory
import com.maxim.jokesapp.interactor.JokeFailureHandler
import com.maxim.jokesapp.joke.BaseCachedJoke
import com.maxim.jokesapp.joke.JokeService
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        Realm.init(this)

        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), JokeRealmMapper())
        val cloudDataSource = BaseCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor = BaseJokeInteractor(repository, JokeFailureFactory(), JokeSuccessMapper())

        viewModel = MainViewModel(interactor, BaseCommunication())
    }
}