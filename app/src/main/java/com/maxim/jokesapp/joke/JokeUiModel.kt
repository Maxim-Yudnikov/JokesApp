package com.maxim.jokesapp.joke

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.Communication
import com.maxim.jokesapp.R
import com.maxim.jokesapp.State

class BaseJokeUiModel(private val text: String, private val punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_border_24
}

class FavoriteJokeUiModel(private val text: String, private val punchline: String) :
    JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_24
}

class FailedJokeUiModel(private val text: String) : JokeUiModel(text, "") {
    override fun getIconResId() = 0
    override fun text() = text
    override fun show(communication: Communication) = communication.showState(State.Failed(text(), getIconResId()))
}


abstract class JokeUiModel(private val text: String, private val punchline: String) {
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int

    open fun show(communication: Communication) = communication.showState(State.Initial(text(), getIconResId()))
}