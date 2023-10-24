package com.maxim.jokesapp.cloud

import com.maxim.jokesapp.Result
import com.maxim.jokesapp.joke.JokeServerModel
import com.maxim.jokesapp.joke.JokeService
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {
    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
        return try {
            val result = service.getJoke().execute().body()!!
            Result.Success(result)
        } catch (e: Exception) {
            val errorType =
                if (e is UnknownHostException) ErrorType.NO_CONNECTION else ErrorType.SERVICE_UNAVAILABLE
            Result.Error(errorType)
        }
    }
}