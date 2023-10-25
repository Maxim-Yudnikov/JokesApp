package com.maxim.jokesapp.domain

import io.realm.internal.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedJokesException: IOException()