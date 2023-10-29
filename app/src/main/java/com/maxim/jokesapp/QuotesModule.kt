package com.maxim.jokesapp

import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.core.domain.FailureHandler
import com.maxim.jokesapp.data.BaseRepository
import com.maxim.jokesapp.data.CommonSuccessMapper
import com.maxim.jokesapp.data.cache.BaseCacheData
import com.maxim.jokesapp.data.cache.QuoteCachedDataSource
import com.maxim.jokesapp.data.cache.QuoteRealmToCommonDataMapper
import com.maxim.jokesapp.data.mapper.QuoteRealmMapper
import com.maxim.jokesapp.data.net.QuoteCloudDataSource
import com.maxim.jokesapp.data.net.QuoteService
import com.maxim.jokesapp.domain.BaseInteractor
import com.maxim.jokesapp.presentation.BaseCommunication
import retrofit2.Retrofit

class QuotesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
) : BaseModule<String, QuotesViewModel>() {
    private var communication: BaseCommunication<String>? = null
    override fun getViewModel(): QuotesViewModel {
        return QuotesViewModel(getInteractor(), getCommunication())
    }

    override fun getCommunication(): BaseCommunication<String> {
        if (communication == null)
            communication = BaseCommunication()
        return communication!!
    }


    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() =
        BaseRepository(getCachedDataSource(), getCloudDataSource(), BaseCacheData())

    private fun getCachedDataSource() =
        QuoteCachedDataSource(realmProvider, QuoteRealmToCommonDataMapper(), QuoteRealmMapper())

    private fun getCloudDataSource() = QuoteCloudDataSource(retrofit.create(QuoteService::class.java))
}