package com.maxim.jokesapp.domain

import com.maxim.jokesapp.JokeFailure
import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.BaseJokeUiModel
import com.maxim.jokesapp.FailedJokeUiModel
import com.maxim.jokesapp.FavoriteJokeUiModel
import com.maxim.jokesapp.JokeUiModel

sealed class Joke : Mapper<JokeUiModel> {
    class Success(
        private val text: String,
        private val punchline: String,
        private val favorite: Boolean
    ) : Joke() {
        override fun to(): JokeUiModel {
            return if(favorite)
                FavoriteJokeUiModel(text, punchline)
            else
                BaseJokeUiModel(text, punchline)
        }

    }

    class Failed(private val failure: JokeFailure) : Joke() {
        override fun to(): JokeUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }
    }
}