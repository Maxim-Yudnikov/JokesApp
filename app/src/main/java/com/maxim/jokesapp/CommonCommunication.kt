package com.maxim.jokesapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.maxim.jokesapp.presentation.CommonUiModel
import com.maxim.jokesapp.presentation.State

interface CommonCommunication<T> : Communication, ListCommunication<T>

interface Communication {
    fun showState(state: State)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun isState(type: Int): Boolean
}

interface ListCommunication<T> {
    fun getList(): List<CommonUiModel<T>>
    fun showDataList(list: List<CommonUiModel<T>>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)
    fun removeItem(id: T) : Int
}