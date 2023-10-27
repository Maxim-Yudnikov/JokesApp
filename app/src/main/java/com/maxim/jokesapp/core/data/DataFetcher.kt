package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel

interface DataFetcher {
    suspend fun getData(): CommonDataModel
}