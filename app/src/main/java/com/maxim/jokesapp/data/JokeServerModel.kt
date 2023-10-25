package com.maxim.jokesapp.data

import com.google.gson.annotations.SerializedName
import com.maxim.jokesapp.core.Mapper

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("punchline")
    private val punchline: String,
) : Mapper<JokeDataModel> {
    override fun to() = JokeDataModel(id, text, punchline)
}