package com.maxim.jokesapp.data.net

import com.google.gson.annotations.SerializedName
import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.CommonDataModel

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("punchline")
    private val punchline: String,
) : Mapper<CommonDataModel> {
    override fun to() = CommonDataModel(id, text, punchline)
}