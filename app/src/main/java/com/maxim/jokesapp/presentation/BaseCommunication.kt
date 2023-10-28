package com.maxim.jokesapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.maxim.jokesapp.CommonCommunication
import okhttp3.internal.lockAndWaitNanos

class BaseCommunication<T> : CommonCommunication<T> {
    private val liveData = MutableLiveData<State>()
    private val listLiveData = MutableLiveData<ArrayList<CommonUiModel<T>>>()

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun showDataList(list: List<CommonUiModel<T>>) {
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

    override fun getList(): List<CommonUiModel<T>> {
        return listLiveData.value ?: emptyList()
    }

    override fun removeItem(id: T): Int {
        val found = listLiveData.value?.find { it.matches(id) }
        val position = listLiveData.value?.indexOf(found) ?: -1
        Log.d("MyLog", "Position: $position")
        found.let { listLiveData.value?.remove(it) }
        return position
    }
}