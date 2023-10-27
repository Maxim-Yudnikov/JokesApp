package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel

interface CommonRepository<E> {
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun changeStatus(): CommonDataModel<E>
    fun chooseDataSource(cached: Boolean)
}