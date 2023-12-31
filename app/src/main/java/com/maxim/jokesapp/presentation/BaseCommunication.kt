package com.maxim.jokesapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.maxim.jokesapp.core.presentation.CommonCommunication

class BaseCommunication<T> : CommonCommunication<T> {
    private val liveData = MutableLiveData<State>()
    private val listLiveData = MutableLiveData<ArrayList<CommonUiModel<T>>>()
    private lateinit var diffResult: DiffUtil.DiffResult

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun showDataList(list: List<CommonUiModel<T>>) {
        val callback = CommonDiffUtilCallback(listLiveData.value ?: emptyList(), list)
        diffResult = DiffUtil.calculateDiff(callback)
        listLiveData.value = ArrayList(list)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>) {
        listLiveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }

    override fun getDiffResult() = diffResult

    override fun getList(): List<CommonUiModel<T>> {
        return listLiveData.value ?: emptyList()
    }
}