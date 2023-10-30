package com.maxim.jokesapp.core.data.cache

import com.maxim.jokesapp.data.CommonDataModel

interface FirebaseToCommonDataMapper<T: FirebaseObject, E> {
    fun map(firebaseObject: T): CommonDataModel<E>
}