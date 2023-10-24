package com.maxim.jokesapp

import android.app.Application
import com.maxim.jokesapp.cache.BaseCachedDataSource
import com.maxim.jokesapp.cloud.BaseCloudDataSource
import com.maxim.jokesapp.cloud.BaseRealmProvider
import com.maxim.jokesapp.joke.JokeService
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        Realm.init(this)

        viewModel = ViewModel(
            BaseModel(
                BaseCachedDataSource(BaseRealmProvider()),
                BaseCloudDataSource(retrofit.create(JokeService::class.java))
            )
        )
    }

    //todo закрыть реалм
}