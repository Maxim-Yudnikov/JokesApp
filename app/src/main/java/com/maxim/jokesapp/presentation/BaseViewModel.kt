package com.maxim.jokesapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxim.jokesapp.core.domain.CommonInteractor
import com.maxim.jokesapp.core.presentation.CommonViewModel
import com.maxim.jokesapp.core.presentation.Communication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel(
    private val interactor: CommonInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel {

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL)) {
                interactor.changeFavorites().to().show(communication)
            }
        }
    }

    override fun getItem() {
        viewModelScope.launch {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    override fun chooseFavorites(favorites: Boolean) {
        viewModelScope.launch(dispatcher) {
            interactor.getFavorites(favorites)
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }
}