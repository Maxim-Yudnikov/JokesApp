package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel

interface ChangeStatus {
    suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel
}