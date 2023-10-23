//package com.maxim.jokesapp
//
//class TestJokeService : JokeService {
//    private var count = 0
//    override fun getJoke(callback: ServiceCallback) {
//        when (count) {
//            0 -> callback.returnSuccess("TestJokeFromServer")
//            1 -> callback.returnError(ErrorType.NO_CONNECTION)
//            2 -> callback.returnError(ErrorType.OTHER)
//        }
//        count++
//        if(count == 3) count = 0
//    }
//}