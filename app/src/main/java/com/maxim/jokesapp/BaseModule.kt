package com.maxim.jokesapp

import com.maxim.jokesapp.presentation.BaseCommunication
import com.maxim.jokesapp.presentation.BaseViewModel

abstract class BaseModule<E, T : BaseViewModel<E>> {
    abstract fun getViewModel(): T
    abstract fun getCommunication(): BaseCommunication<E>
}