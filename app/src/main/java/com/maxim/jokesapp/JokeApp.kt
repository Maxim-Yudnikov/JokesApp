package  com.maxim.jokesapp

import android.app.Application
import com.maxim.jokesapp.data.BaseRepository
import com.maxim.jokesapp.data.CommonSuccessMapper
import com.maxim.jokesapp.data.cache.BaseCacheData
import com.maxim.jokesapp.data.cache.BaseRealmProvider
import com.maxim.jokesapp.data.cache.JokeCachedDataSource
import com.maxim.jokesapp.data.cache.JokeRealmToCommonDataMapper
import com.maxim.jokesapp.data.cache.QuoteCachedDataSource
import com.maxim.jokesapp.data.cache.QuoteRealmToCommonDataMapper
import com.maxim.jokesapp.data.mapper.JokeRealmMapper
import com.maxim.jokesapp.data.mapper.QuoteRealmMapper
import com.maxim.jokesapp.data.net.JokeCloudDataSource
import com.maxim.jokesapp.data.net.JokeService
import com.maxim.jokesapp.data.net.QuoteCloudDataSource
import com.maxim.jokesapp.data.net.QuoteService
import com.maxim.jokesapp.domain.BaseInteractor
import com.maxim.jokesapp.domain.FailureFactory
import com.maxim.jokesapp.presentation.BaseCommunication
import com.maxim.jokesapp.presentation.BaseViewModel
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        Realm.init(this)

        val failureHandler = FailureFactory()
        val mapper = CommonSuccessMapper()
        val realmProvider = BaseRealmProvider()

        val jokeCacheDataSource =
            JokeCachedDataSource(realmProvider, JokeRealmToCommonDataMapper(), JokeRealmMapper())
        //val cloudDataSource = NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        val cloudDataSource = JokeCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseRepository(jokeCacheDataSource, cloudDataSource, BaseCacheData())
        val interactor = BaseInteractor(repository, failureHandler, mapper)

        viewModel = BaseViewModel(interactor, BaseCommunication())

        val quoteRepository = BaseRepository(
            QuoteCachedDataSource(
                realmProvider,
                QuoteRealmToCommonDataMapper(),
                QuoteRealmMapper()
            ),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCacheData()
        )

        quoteViewModel = BaseViewModel(
            BaseInteractor(
                quoteRepository,
                failureHandler,
                mapper
            ), BaseCommunication()
        )
    }
}