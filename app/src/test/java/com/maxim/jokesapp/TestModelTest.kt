package com.maxim.jokesapp

import com.maxim.jokesapp.joke.JokeUiModel
import com.maxim.jokesapp.test.TestModel
import org.junit.Assert.*
import org.junit.Test

class TestModelTest {
    @Test
    fun test() {
        val model = TestModel()
        val fakeCallback = FakeResultCallback()
        model.init(fakeCallback)

        model.getJoke()
        Thread.sleep(1100)
        assertEquals("testJokeText\ntextPunchline", fakeCallback.text)
        assertEquals("success", fakeCallback.type)

        model.getJoke()
        Thread.sleep(1100)
        assertEquals("No Connection Error", fakeCallback.text)
        assertEquals("error", fakeCallback.type)

        model.getJoke()
        Thread.sleep(1100)
        assertEquals("Service Unavailable Error", fakeCallback.text)
        assertEquals("error", fakeCallback.type)
    }
}

private class FakeResultCallback: JokeCallback{
    var text: String = ""
    var type: String = ""
    override fun provideSuccess(data: JokeUiModel) {
        text = data.mapToUi()
        type = "success"
    }

    override fun provideError(error: JokeFailure) {
        text = error.getMessage()
        type = "error"
    }
}