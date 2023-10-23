//package com.maxim.jokesapp.test
//
//import com.maxim.jokesapp.BaseJoke
//import com.maxim.jokesapp.FailedJoke
//import com.maxim.jokesapp.FavoriteJoke
//import com.maxim.jokesapp.Model
//import com.maxim.jokesapp.NoConnection
//import com.maxim.jokesapp.JokeCallback
//import com.maxim.jokesapp.ServiceUnavailable
//
//class TestModel : Model {
//
//    private var callback: JokeCallback? = null
//    private var count = 0
//    private val noConnection = NoConnection()
//    private val serviceUnavailable = ServiceUnavailable()
//    override fun getJoke() {
//        Thread {
//            Thread.sleep(1000)
//            when(count){
//                0 -> callback?.provide(BaseJoke("testJokeText", "textPunchline"))
//                1 -> callback?.provide(FavoriteJoke("testFavoriteJokeText", "textFavoritePunchline"))
//                2 -> callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
//            }
//            count++
//            if(count == 3) count = 0
//        }.start()
//    }
//
//    override fun clear() {
//        callback = null
//    }
//
//    override fun changeJokeStatus(jokeCallback: JokeCallback) {
//
//    }
//
//    override fun init(callback: JokeCallback) {
//        this.callback = callback
//    }
//}