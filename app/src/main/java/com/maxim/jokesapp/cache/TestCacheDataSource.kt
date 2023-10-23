//package com.maxim.jokesapp.cache
//
//import com.maxim.jokesapp.JokeUiModel
//import com.maxim.jokesapp.joke.JokeServerModel
//
//class TestCacheDataSource : CachedDataSource {
//    private val list = ArrayList<Pair<Int, JokeServerModel>>()
//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        if(list.isEmpty())
//            jokeCachedCallback.fail()
//        else
//            jokeCachedCallback.provide(list.random().second)
//    }
//
//    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): JokeUiModel {
//        val found = list.find { it.first == id }
//        return if (found != null) {
//            val joke = found.second.toBaseJoke()
//            list.remove(found)
//            joke
//        } else {
//            list.add(Pair(id, jokeServerModel))
//            jokeServerModel.toFavoriteJoke()
//        }
//    }
//}