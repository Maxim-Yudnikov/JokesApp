package  com.maxim.jokesapp

import android.app.Application
import com.maxim.jokesapp.data.BaseJokeRepository
import com.maxim.jokesapp.data.JokeRealmMapper
import com.maxim.jokesapp.data.JokeSuccessMapper
import com.maxim.jokesapp.data.cache.BaseCacheDataSource
import com.maxim.jokesapp.data.cloud.BaseRealmProvider
import com.maxim.jokesapp.interactor.BaseJokeInteractor
import com.maxim.jokesapp.interactor.JokeFailureFactory
import com.maxim.jokesapp.data.cache.BaseCachedJoke
import com.maxim.jokesapp.data.cloud.BaseJokeService
import com.maxim.jokesapp.data.cloud.BaseJokeCloudDataSource
import com.maxim.jokesapp.data.cloud.NewJokeCloudDataSource
import com.maxim.jokesapp.data.cloud.NewJokeService
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        Realm.init(this)

        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), JokeRealmMapper())
        val cloudDataSource = NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        //val cloudDataSource = BaseJokeCloudDataSource(retrofit.create(BaseJokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor = BaseJokeInteractor(repository, JokeFailureFactory(), JokeSuccessMapper())

        viewModel = MainViewModel(interactor, BaseCommunication())
    }
}