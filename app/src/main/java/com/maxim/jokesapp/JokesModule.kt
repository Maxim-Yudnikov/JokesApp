package com.maxim.jokesapp

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.core.domain.FailureHandler
import com.maxim.jokesapp.data.BaseRepository
import com.maxim.jokesapp.data.CommonSuccessMapper
import com.maxim.jokesapp.data.cache.BaseCacheData
import com.maxim.jokesapp.data.cache.FirebaseCacheDataSource
import com.maxim.jokesapp.data.cache.JokeCachedDataSource
import com.maxim.jokesapp.data.cache.JokeFirebaseToCommonDataMapper
import com.maxim.jokesapp.data.cache.JokeRealmToCommonDataMapper
import com.maxim.jokesapp.data.mapper.JokeFirebaseMapper
import com.maxim.jokesapp.data.mapper.JokeRealmMapper
import com.maxim.jokesapp.data.net.JokeCloudDataSource
import com.maxim.jokesapp.data.net.JokeService
import com.maxim.jokesapp.domain.BaseInteractor
import com.maxim.jokesapp.mock.MockJokeCloudDataSource
import com.maxim.jokesapp.presentation.BaseCommunication
import retrofit2.Retrofit

class JokesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit,
    private val useMocks: Boolean
) : BaseModule<Int, JokesViewModel>() {
    private var communication: BaseCommunication<Int>? = null
    override fun getViewModel(): JokesViewModel {
        return JokesViewModel(getInteractor(), getCommunication())
    }

    override fun getCommunication(): BaseCommunication<Int> {
        if (communication == null)
            communication = BaseCommunication()
        return communication!!
    }


    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() =
        BaseRepository(getCachedDataSource(), getCloudDataSource(), BaseCacheData())

    private fun getCachedDataSource() =
        //JokeCachedDataSource(realmProvider, JokeRealmToCommonDataMapper(), JokeRealmMapper())
        FirebaseCacheDataSource(getFirebase(), JokeFirebaseToCommonDataMapper(), JokeFirebaseMapper())

    private fun getCloudDataSource() = if (useMocks)
        MockJokeCloudDataSource()
    else
        JokeCloudDataSource(retrofit.create(JokeService::class.java))

    private fun getFirebase() = FirebaseDatabase.getInstance().getReference("Jokes")
}