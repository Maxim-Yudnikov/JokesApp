package com.maxim.jokesapp.core.data.cache

import com.maxim.jokesapp.core.data.ChangeCommonItem
import com.maxim.jokesapp.data.CommonDataModel
import java.lang.IllegalStateException

interface CachedData<E> : ChangeCommonItem<E> {
    fun save(data: CommonDataModel<E>)
    fun clear()
}