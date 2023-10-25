package com.maxim.jokesapp.data.cloud

import com.maxim.jokesapp.Result
import com.maxim.jokesapp.data.JokeDataFetcher
import com.maxim.jokesapp.data.JokeDataModel
import com.maxim.jokesapp.data.JokeServerModel
import com.maxim.jokesapp.domain.NoConnectionException
import com.maxim.jokesapp.domain.ServiceUnavailableException
import com.maxim.jokesapp.joke.JokeService
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {
    override suspend fun getJoke(): JokeDataModel {
        return try {
            return service.getJoke().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}