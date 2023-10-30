package com.maxim.jokesapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxim.jokesapp.core.presentation.CommonCommunication
import com.maxim.jokesapp.core.domain.CommonInteractor
import com.maxim.jokesapp.core.presentation.CommonViewModel
import com.maxim.jokesapp.domain.CommonItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(
    private val name: String,
    private val interactor: CommonInteractor<T>,
    val communication: CommonCommunication<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel<T> {
    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL)) {
                val oldListCount = interactor.getItemList().count()
                interactor.changeFavorites().to().show(communication)
                while (oldListCount == interactor.getItemList().count()) {}
                communication.showDataList(interactor.getItemList().toUiList())
            }
        }
    }

//    init {
//        Log.d("MyLog", "init $name")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d("MyLog", "onCleared $name")
//    }


    override fun changeItemStatus(id: T) {
        viewModelScope.launch(dispatcher) {
            val oldListCount = interactor.getItemList().count()
            interactor.removeItem(id)
            while (oldListCount == interactor.getItemList().count()) {}
            communication.showDataList(interactor.getItemList().toUiList())
        }
    }

    override fun getItem() {
        viewModelScope.launch {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    override fun getItemList() {
        viewModelScope.launch(dispatcher) {
            Log.d("MyLog", "3interactor.getItemList() length: ${interactor.getItemList().count()}")
            communication.showDataList(interactor.getItemList().toUiList())
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

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>) {
        communication.observeList(owner, observer)
    }
}

fun <E> List<CommonItem<E>>.toUiList() = map { it.to() }