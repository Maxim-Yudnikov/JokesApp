package com.maxim.jokesapp.core.domain

import com.maxim.jokesapp.domain.CommonItem

interface CommonInteractor<E> {
    suspend fun removeItem(id: E)
    suspend fun getItem(): CommonItem<E>
    suspend fun getItemList(): List<CommonItem<E>>
    suspend fun changeFavorites(): CommonItem<E>
    suspend fun getFavorites(favorites: Boolean)
}