package com.maxim.jokesapp.core.domain

import com.maxim.jokesapp.presentation.BaseFailure

interface FailureHandler {
    fun handle(e: Exception): BaseFailure
}