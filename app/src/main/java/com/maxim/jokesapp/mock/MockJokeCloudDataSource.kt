package com.maxim.jokesapp.mock

import com.maxim.jokesapp.data.net.BaseCloudDataSource
import com.maxim.jokesapp.data.net.JokeServerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class MockJokeCloudDataSource: BaseCloudDataSource<JokeServerModel, Int>() {
    private var id: Int = -1
    override fun getServerModel(): Call<JokeServerModel> {
        return object : SimpleCall<JokeServerModel> {
            override fun execute(): Response<JokeServerModel> {
                ++id
                return Response.success(
                    JokeServerModel(id, "mockType", "mock text $id", "mock punchline $id")
                )
            }
        }
    }
}

interface SimpleCall<T> : Call<T> {
    override fun clone() = throw IllegalStateException("not used")
    override fun isExecuted() = false
    override fun cancel() = Unit
    override fun isCanceled() = false
    override fun request() = throw IllegalStateException("not used")
    override fun enqueue(callback: Callback<T>) = Unit
    override fun timeout() = throw IllegalStateException("not used")
}