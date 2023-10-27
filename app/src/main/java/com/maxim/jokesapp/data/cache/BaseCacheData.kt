package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.ChangeCommonItem
import com.maxim.jokesapp.core.data.ChangeStatus
import com.maxim.jokesapp.core.data.cache.CachedData
import com.maxim.jokesapp.data.CommonDataModel

class BaseCacheData<E>: CachedData<E> {
    private var cached: ChangeCommonItem<E> = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel<E>) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
        return cached.change(changeStatus)
    }
}