package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.FirebaseToCommonDataMapper
import com.maxim.jokesapp.data.CommonDataModel

class JokeFirebaseToCommonDataMapper: FirebaseToCommonDataMapper<JokeFirebaseModel, Int> {
    override fun map(firebaseObject: JokeFirebaseModel): CommonDataModel<Int> = CommonDataModel(
        firebaseObject.id!!.toInt(), firebaseObject.text!!, firebaseObject.punchline!!, true
    )
}