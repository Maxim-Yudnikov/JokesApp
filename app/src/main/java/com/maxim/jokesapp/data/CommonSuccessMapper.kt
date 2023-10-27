package com.maxim.jokesapp.data

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.domain.CommonItem

class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, first: String, second: String, cached: Boolean) =
        CommonItem.Success(first, second, cached)
}