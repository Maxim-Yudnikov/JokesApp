package com.maxim.jokesapp.data.cloud

import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.JokeDataModel
import retrofit2.Call
import retrofit2.http.GET


interface BaseJokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeServerModel>
}

interface NewJokeService {
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke(): Call<NewJokeServerModel>
}