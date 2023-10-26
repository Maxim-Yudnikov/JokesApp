package com.maxim.jokesapp

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxim.jokesapp.data.JokeRepository
import com.maxim.jokesapp.interactor.JokeInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun changeJokeStatus() = viewModelScope.launch(dispatcher) {
        if (communication.isState(State.INITIAL)) {
            interactor.changeFavorites().to().show(communication)
        }
    }

    fun chooseFavorites(favorites: Boolean) = viewModelScope.launch(dispatcher) {
        interactor.getFavoritesJokes(favorites)
    }

    fun getJoke() = viewModelScope.launch {
        communication.showState(State.Progress)
        interactor.getJoke().to().show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }
}