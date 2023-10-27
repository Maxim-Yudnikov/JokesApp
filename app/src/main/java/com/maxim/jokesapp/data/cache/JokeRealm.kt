package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.CommonDataModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchline: String = ""
}