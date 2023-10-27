package com.maxim.jokesapp.data.net

import com.google.gson.annotations.SerializedName
import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.CommonDataModel

class QuoteServerModel(
    @SerializedName("_id")
    private val id: String,
    @SerializedName("content")
    private val content: String,
    @SerializedName("author")
    private val author: String,
) : Mapper<CommonDataModel> {
        override fun to() = CommonDataModel(System.currentTimeMillis().toInt(), content, author)
}