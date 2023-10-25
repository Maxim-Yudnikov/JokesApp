package com.maxim.jokesapp

import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val model: Model,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun changeJokeStatus() = viewModelScope.launch {
        model.changeJokeStatus()?.let {
            it.show(communication)
        }
    }

    fun chooseFavorites(favorites: Boolean) = model.chooseDataSource(favorites)

    fun getJoke() = viewModelScope.launch {
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }
}


interface DataCallback {
    fun provideText(test: String)
    fun provideIconRes(@DrawableRes id: Int)
}