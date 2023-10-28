package com.maxim.jokesapp.domain

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.core.data.CommonRepository
import com.maxim.jokesapp.core.domain.CommonInteractor
import com.maxim.jokesapp.core.domain.FailureHandler

class BaseInteractor<T>(
    private val repository: CommonRepository<T>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success<T>, T>
) : CommonInteractor<T> {
    override suspend fun removeItem(id: T) {
        repository.removeItem(id)
    }


    override suspend fun getItem(): CommonItem<T> {
        return try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun getItemList(): List<CommonItem<T>> {
        return try {
            repository.getCommonItemList().map { it.map(mapper) }
        } catch (e: Exception) {
            listOf(CommonItem.Failed(failureHandler.handle(e)))
        }
    }

    override suspend fun changeFavorites(): CommonItem<T> {
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