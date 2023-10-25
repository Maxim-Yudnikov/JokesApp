package com.maxim.jokesapp.data

import com.maxim.jokesapp.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm: RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id: Int = -2
    var text: String = ""
    var punchline: String = ""

    override fun to() = JokeDataModel(id, text, punchline, true)
}