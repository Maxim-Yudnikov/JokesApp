package com.maxim.jokesapp.interactor

import com.maxim.jokesapp.domain.Joke

interface JokeInteractor {
    suspend fun getJoke(): Joke
    suspend fun changeFavorites(): Joke
    suspend fun getFavoritesJokes(favorites: Boolean)
}