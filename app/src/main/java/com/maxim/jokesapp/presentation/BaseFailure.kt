package com.maxim.jokesapp.presentation

interface BaseFailure {
    fun getMessage(): String
}

class NoConnection : BaseFailure {
    override fun getMessage() = "No Connection Error"
}

class ServiceUnavailable : BaseFailure {
    override fun getMessage() = "Service Unavailable Error"
}

class NoCachedData : BaseFailure {
    override fun getMessage() = "No favorites! Add one by heart icon"
}

class GenericError() : BaseFailure {
    override fun getMessage(): String = "unknown exception"
}