package com.maxim.jokesapp.core.domain

import com.maxim.jokesapp.domain.CommonItem

interface CommonInteractor {
    suspend fun getItem(): CommonItem
    suspend fun changeFavorites(): CommonItem
    suspend fun getFavorites(favorites: Boolean)
}