package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel

interface CommonRepository {
    suspend fun getCommonItem(): CommonDataModel
    suspend fun changeStatus(): CommonDataModel
    fun chooseDataSource(cached: Boolean)
}