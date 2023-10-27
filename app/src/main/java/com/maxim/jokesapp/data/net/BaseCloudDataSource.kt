package com.maxim.jokesapp.data.net

import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.core.data.net.CloudDataSource
import com.maxim.jokesapp.data.CommonDataModel
import com.maxim.jokesapp.core.domain.NoConnectionException
import com.maxim.jokesapp.core.domain.ServiceUnavailableException
import retrofit2.Call
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<CommonDataModel<E>>, E> : CloudDataSource<E> {
    protected abstract fun getServerModel(): Call<T>
    override suspend fun getData(): CommonDataModel<E> {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}