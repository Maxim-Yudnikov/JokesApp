package com.maxim.jokesapp.data.cloud

import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.data.JokeDataModel
import com.maxim.jokesapp.domain.NoConnectionException
import com.maxim.jokesapp.domain.ServiceUnavailableException
import retrofit2.Call
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<JokeDataModel>> : CloudDataSource {
    protected abstract fun getJokeServerModel(): Call<T>
    override suspend fun getJoke(): JokeDataModel {
        try {
            return getJokeServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}