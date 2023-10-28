package com.maxim.jokesapp.core.data.cache

import com.maxim.jokesapp.core.data.ChangeStatus
import com.maxim.jokesapp.core.data.DataFetcher
import com.maxim.jokesapp.data.CommonDataModel

interface CacheDataSource<E> : ChangeStatus<E>, DataFetcher<E> {
    suspend fun getDataList(): List<CommonDataModel<E>>
}