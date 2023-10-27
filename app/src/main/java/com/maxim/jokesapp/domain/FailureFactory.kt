package com.maxim.jokesapp.domain

import com.maxim.jokesapp.presentation.BaseFailure
import com.maxim.jokesapp.presentation.GenericError
import com.maxim.jokesapp.presentation.NoCachedData
import com.maxim.jokesapp.presentation.NoConnection
import com.maxim.jokesapp.presentation.ServiceUnavailable
import com.maxim.jokesapp.core.domain.FailureHandler
import com.maxim.jokesapp.core.domain.NoCachedDataException
import com.maxim.jokesapp.core.domain.NoConnectionException
import com.maxim.jokesapp.core.domain.ServiceUnavailableException

class FailureFactory() : FailureHandler {
    override fun handle(e: Exception): BaseFailure {
        return when(e){
            is NoConnectionException -> NoConnection()
            is NoCachedDataException -> NoCachedData()
            is ServiceUnavailableException -> ServiceUnavailable()
            else -> GenericError()
        }
    }
}