package com.maxim.jokesapp.joke

import com.maxim.jokesapp.data.JokeServerModel
import retrofit2.Call
import retrofit2.http.GET


interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeServerModel>
}