package com.maxim.jokesapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.maxim.jokesapp.presentation.CommonUiModel

interface ListCommunication {
    fun showDataList(list: List<CommonUiModel>)

    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel>>)
}