package com.maxim.jokesapp.data.cache

import com.maxim.jokesapp.core.data.cache.FirebaseObject

data class JokeFirebaseModel(
    val time: String? = null,
    val id: String? = null,
    val text: String? = null,
    val punchline: String? = null
) : FirebaseObject