package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.CommonDataModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealmModel: RealmObject() {
    @PrimaryKey
    var id: String = ""
    var content: String = ""
    var author: String = ""
}