package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.CommonDataModelMapper
import com.maxim.jokesapp.core.data.cache.CacheDataSource
import com.maxim.jokesapp.core.data.cache.RealmProvider
import com.maxim.jokesapp.core.data.cache.RealmToCommonDataMapper
import com.maxim.jokesapp.core.domain.NoCachedDataException
import com.maxim.jokesapp.data.CommonDataModel
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseCacheDataSource<T : RealmObject, E>(
    private val realmProvider: RealmProvider,
    private val realmToCommonMapper: RealmToCommonDataMapper<T, E>,
    private val mapper: CommonDataModelMapper<T, E>
) : CacheDataSource<E> {

    protected abstract val dbClass: Class<T>
    override suspend fun getData() = getRealmData { results ->
        realmToCommonMapper.map(results.random())
    }

    override suspend fun remove(id: E) = withContext(Dispatchers.IO) {
        realmProvider.provide().use { realm ->
            realm.executeTransaction {
                findRealmObject(realm, id)?.deleteFromRealm()
            }
        }
    }

    override suspend fun getDataList() = getRealmData { results ->
        results.map { realmToCommonMapper.map(it) }
    }

    protected abstract fun findRealmObject(realm: Realm, id: E): T?

    override suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E> =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm = findRealmObject(it, id)
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

    private fun <R> getRealmData(block: (list: RealmResults<T>) -> R) : R {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if(list.isEmpty())
                throw NoCachedDataException()
            else
                return block.invoke(list)
        }
    }
}