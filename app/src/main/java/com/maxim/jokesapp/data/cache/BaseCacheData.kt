package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.ChangeCommonItem
import com.maxim.jokesapp.core.data.ChangeStatus
import com.maxim.jokesapp.core.data.cache.CachedData
import com.maxim.jokesapp.data.CommonDataModel

class BaseCacheData: CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeJokeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeJokeStatus)
    }
}