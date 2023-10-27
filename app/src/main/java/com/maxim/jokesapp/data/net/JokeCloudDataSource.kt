package com.maxim.jokesapp.data.net

class NewJokeCloudDataSource(private val service: NewJokeService) :
    BaseCloudDataSource<NewJokeServerModel, Int>() {
    override fun getServerModel() = service.getJoke()
}

class JokeCloudDataSource(private val service: JokeService) :
    BaseCloudDataSource<JokeServerModel, Int>() {
    override fun getServerModel() = service.getJoke()
}