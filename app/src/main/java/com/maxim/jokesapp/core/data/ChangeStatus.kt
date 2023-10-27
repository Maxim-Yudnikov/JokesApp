package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel

interface ChangeStatus<E> {
    suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E>
}