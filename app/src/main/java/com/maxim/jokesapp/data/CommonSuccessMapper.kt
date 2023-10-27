package com.maxim.jokesapp.data

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.domain.CommonItem

class CommonSuccessMapper<E> : CommonDataModelMapper<CommonItem.Success, E> {
    override fun map(id: E, first: String, second: String, cached: Boolean) =
        CommonItem.Success(first, second, cached)
}