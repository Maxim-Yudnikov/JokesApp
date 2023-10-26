package com.maxim.jokesapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.JokeDataModel

data class NewJokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("delivery")
    private val punchline: String,
) : Mapper<JokeDataModel> {
    override fun to() = JokeDataModel(id, text, punchline)
}