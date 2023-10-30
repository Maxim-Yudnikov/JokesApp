package com.maxim.jokesapp.data.cache

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.core.data.cache.CacheDataSource
import com.maxim.jokesapp.core.data.cache.FirebaseToCommonDataMapper
import com.maxim.jokesapp.data.CommonDataModel
import java.lang.IllegalStateException

class FirebaseCacheDataSource<E>(
    private val dbRef: DatabaseReference,
    private val firebaseToCommonMapper: FirebaseToCommonDataMapper<JokeFirebaseModel, E>,
    private val commonMapper: CommonDataModelMapper<JokeFirebaseModel, E>
) : CacheDataSource<E> {
    private val dataList = arrayListOf<JokeFirebaseModel>()

    init {
        dbRef.orderByChild("time").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("MyLog", "DataList update")
                dataList.clear()
                if (snapshot.exists()) {
                    snapshot.children.forEach {
                        dataList.add(it.getValue(JokeFirebaseModel::class.java)!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {Log.d("MyLog", "canceled")}
        })
    }

    override suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E> {
        return if (dataList.filter { it.id == id.toString() }.isEmpty()) {
            val firebaseData = model.map(commonMapper)
            dbRef.child(id.toString()).setValue(firebaseData)
            model.changeCached(true)
        } else {
            dbRef.child(id.toString()).removeValue()
            model.changeCached(false)
        }
    }

    override suspend fun remove(id: E) {
        dbRef.child(id.toString()).removeValue()
    }

    override suspend fun getDataList(): List<CommonDataModel<E>> {
        return dataList.map { firebaseToCommonMapper.map(it) }
    }

    override suspend fun getData(): CommonDataModel<E> = throw IllegalStateException("not done")
}