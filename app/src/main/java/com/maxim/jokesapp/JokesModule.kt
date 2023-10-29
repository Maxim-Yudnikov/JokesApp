package com.maxim.jokesapp

import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.core.domain.FailureHandler
import com.maxim.jokesapp.data.BaseRepository
import com.maxim.jokesapp.data.CommonSuccessMapper
import com.maxim.jokesapp.data.cache.BaseCacheData
import com.maxim.jokesapp.data.cache.JokeCachedDataSource
import com.maxim.jokesapp.data.cache.JokeRealmToCommonDataMapper
import com.maxim.jokesapp.data.mapper.JokeRealmMapper
import com.maxim.jokesapp.data.net.JokeCloudDataSource
import com.maxim.jokesapp.data.net.JokeService
import com.maxim.jokesapp.domain.BaseInteractor
import com.maxim.jokesapp.presentation.BaseCommunication
import retrofit2.Retrofit

class JokesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
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
        JokeCachedDataSource(realmProvider, JokeRealmToCommonDataMapper(), JokeRealmMapper())

    private fun getCloudDataSource() = JokeCloudDataSource(retrofit.create(JokeService::class.java))
}