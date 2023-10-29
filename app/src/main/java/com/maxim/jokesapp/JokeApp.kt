package  com.maxim.jokesapp

import android.app.Application
import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.core.domain.FailureHandler
import com.maxim.jokesapp.core.presentation.CommonCommunication
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

    val viewModelFactory by lazy {
        ViewModelFactory(
            JokesModule(failureHandler, realmProvider, retrofit),
            QuotesModule(failureHandler, realmProvider, retrofit)
        )
    }

    private lateinit var retrofit: Retrofit
    private lateinit var realmProvider: RealmProvider
    private lateinit var failureHandler: FailureHandler

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder().client(client).baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        realmProvider = BaseRealmProvider()
        failureHandler = FailureFactory()
    }
}