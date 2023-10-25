package com.maxim.jokesapp

interface JokeFailure {
    fun getMessage(): String
}

class NoConnection : JokeFailure {
    override fun getMessage() = "No Connection Error"
}

class ServiceUnavailable : JokeFailure {
    override fun getMessage() = "Service Unavailable Error"
}

class NoCachedJokes : JokeFailure {
    override fun getMessage() = "No favorites jokes! Add one by heart icon"
}

class GenericError() : JokeFailure {
    override fun getMessage(): String = "unknown exception"
}