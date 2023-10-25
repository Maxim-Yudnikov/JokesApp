//package com.maxim.jokesapp
//
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.Observer
//import com.maxim.jokesapp.joke.BaseJokeUiModel
//import com.maxim.jokesapp.joke.FailedJokeUiModel
//import com.maxim.jokesapp.joke.JokeUiModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import org.junit.Assert.*
//import org.junit.Test
//
//class MainViewModelTest {
//    @ExperimentalCoroutinesApi
//    @Test
//    fun test_get_joke_from_cloud_success(): Unit = runBlocking {
//        val model = TestModel()
//        val communication = TestCommunication()
//        val viewModel = MainViewModel(model, communication, TestCoroutinesDispatcher())
//
//        model.success = true
//        viewModel.chooseFavorites(false)
//        viewModel.getJoke()
//
//        val actualText = communication.text
//        val actualId = communication.id
//        val expectedText = "cloudJokeText\ncloudJokePunchline"
//        assertEquals(expectedText, actualText)
//        assertNotEquals(0, actualId)
//    }
//
//
//    private inner class TestModel() : Model {
//        private val cachedJokeUiModel = BaseJokeUiModel("cachedJokeText", "cachedJokePunchline")
//        private val cachedJokeFailure = FailedJokeUiModel("cachedJokeFailure")
//        private val cloudJokeUiModel = BaseJokeUiModel("cloudJokeText", "cloudJokePunchline")
//        private val cloudJokeFailure = FailedJokeUiModel("cloudJokeFailure")
//
//        var success = false
//        private var getFromCache = false
//        private var cachedJoke: JokeUiModel? = null
//
//
//        override suspend fun getJoke(): JokeUiModel {
//            return if (success) {
//                if (getFromCache) {
//                    cachedJokeUiModel.also {
//                        cachedJoke = it
//                    }
//                } else {
//                    cloudJokeUiModel.also {
//                        cachedJoke = it
//                    }
//                }
//            } else {
//                cachedJoke = null
//                if (getFromCache)
//                    cachedJokeFailure
//                else
//                    cloudJokeFailure
//            }
//        }
//
//        override suspend fun changeJokeStatus(): JokeUiModel? {
//            return cachedJoke
//        }
//
//        override fun chooseDataSource(cached: Boolean) {
//            getFromCache = cached
//        }
//    }
//
//    private inner class TestCommunication : Communication {
//        var text = ""
//        var id = 0
//        var observedCount = 0
//        override fun showData(data: Pair<String, Int>) {
//            text = data.first
//            id = data.second
//        }
//
//        override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
//            observedCount++
//        }
//    }
//}