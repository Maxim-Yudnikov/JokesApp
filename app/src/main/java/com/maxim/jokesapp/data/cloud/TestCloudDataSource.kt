//package com.maxim.jokesapp.cloud
//
//import com.maxim.jokesapp.data.JokeServerModel
//
//class TestCloudDataSource : CloudDataSource {
//    private var count = 0
//    override fun getJoke(callback: JokeCloudCallback) {
//        callback.provide(
//            JokeServerModel(
//                count,
//                "testType$count",
//                "testText$count",
//                "testPunchline$count"
//            )
//        )
//        count++
//    }
//}