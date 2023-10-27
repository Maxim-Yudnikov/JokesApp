package com.maxim.jokesapp.domain

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.core.data.CommonRepository
import com.maxim.jokesapp.core.domain.CommonInteractor
import com.maxim.jokesapp.core.domain.FailureHandler

class BaseInteractor<E>(
    private val repository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success, E>
): CommonInteractor {
    override suspend fun getItem(): CommonItem {
        return try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): CommonItem {
        return try {
            repository.changeStatus().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun getFavorites(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}