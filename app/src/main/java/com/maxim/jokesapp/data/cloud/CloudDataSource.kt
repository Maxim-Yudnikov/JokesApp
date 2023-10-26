package com.maxim.jokesapp.data.cloud

import com.maxim.jokesapp.data.JokeDataFetcher

interface CloudDataSource : JokeDataFetcher
class NewJokeCloudDataSource(private val service: NewJokeService) :
    BaseCloudDataSource<NewJokeServerModel>() {
    override fun getJokeServerModel() = service.getJoke()
}

class BaseJokeCloudDataSource(private val service: BaseJokeService) :
    BaseCloudDataSource<JokeServerModel>() {
    override fun getJokeServerModel() = service.getJoke()

}