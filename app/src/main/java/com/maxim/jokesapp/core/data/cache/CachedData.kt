package com.maxim.jokesapp.core.data.cache

import com.maxim.jokesapp.core.data.ChangeCommonItem
import com.maxim.jokesapp.data.CommonDataModel
import java.lang.IllegalStateException

interface CachedData : ChangeCommonItem {
    fun save(data: CommonDataModel)
    fun clear()
}