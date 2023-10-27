package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.core.data.cache.CacheDataSource
import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.core.data.cache.RealmToCommonDataMapper
import com.maxim.jokesapp.core.domain.NoCachedDataException
import com.maxim.jokesapp.data.CommonDataModel
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseCacheDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val realmToCommonMapper: RealmToCommonDataMapper<T>,
    private val mapper: CommonDataModelMapper<T>
) : CacheDataSource {

    protected abstract val dbClass: Class<T>
    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else {
                val realmData = it.copyFromRealm(list.random())
                return realmToCommonMapper.map(realmData)
            }
        }
    }

    override suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm = it.where(dbClass).equalTo("id", id).findFirst()
                return@withContext if (itemRealm == null) {
                    it.executeTransaction { transaction ->
                        val newData = model.map(mapper)
                        transaction.insert(newData)
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        itemRealm.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}