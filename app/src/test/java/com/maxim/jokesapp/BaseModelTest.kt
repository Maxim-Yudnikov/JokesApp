package com.maxim.jokesapp

import com.maxim.jokesapp.joke.JokeUiModel
import org.junit.Assert.*
import org.junit.Test

class BaseModelTest {
    @Test
    fun text() {
        val service = TestJokeService()
        val model = BaseModel(service)
        val callback = FakeResultCallback()
        model.init(callback)

        model.getJoke()
        assertEquals("TestJokeFromServer\n", callback.text)
        assertEquals("success", callback.type)

        model.getJoke()
        assertEquals("No Connection Error", callback.text)
        assertEquals("error", callback.type)

        model.getJoke()
        assertEquals("Service Unavailable Error", callback.text)
        assertEquals("error", callback.type)
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
}