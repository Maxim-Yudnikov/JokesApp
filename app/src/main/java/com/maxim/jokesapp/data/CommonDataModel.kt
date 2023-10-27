package com.maxim.jokesapp.data

import com.maxim.jokesapp.core.data.ChangeCommonItem
import com.maxim.jokesapp.core.data.ChangeStatus
import com.maxim.jokesapp.core.data.CommonDataModelMapper

class CommonDataModel<E>(
    private val id: E,
    private val first: String,
    private val second: String,
    private val cached: Boolean = false
) : ChangeCommonItem<E> {

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> =
        changeStatus.addOrRemove(id, this)

    fun <T> map(mapper: CommonDataModelMapper<T, E>): T {
        return mapper.map(id, first, second, cached)
    }

    fun changeCached(cached: Boolean): CommonDataModel<E> {
        return CommonDataModel(id, first, second, cached)
    }
}