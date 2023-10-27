package com.maxim.jokesapp.data

import com.maxim.jokesapp.core.data.ChangeCommonItem
import com.maxim.jokesapp.core.data.ChangeStatus
import com.maxim.jokesapp.core.data.CommonDataModelMapper

class CommonDataModel(
    private val id: Int,
    private val first: String,
    private val second: String,
    private val cached: Boolean = false
) : ChangeCommonItem {

    override suspend fun change(changeStatus: ChangeStatus) =
        changeStatus.addOrRemove(id, this)

    fun <T> map(mapper: CommonDataModelMapper<T>): T {
        return mapper.map(id, first, second, cached)
    }

    fun changeCached(cached: Boolean) : CommonDataModel {
        return CommonDataModel(id, first, second, cached)
    }
}