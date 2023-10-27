package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel

interface DataFetcher<E> {
    suspend fun getData(): CommonDataModel<E>
}