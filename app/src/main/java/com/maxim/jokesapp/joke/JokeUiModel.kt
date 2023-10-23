package com.maxim.jokesapp.joke

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.DataCallback
import com.maxim.jokesapp.R

class BaseJokeUiModel(private val text: String, private val punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_border_24
}

class FavoriteJokeUiModel(private val text: String, private val punchline: String) :
    JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_24
}

class FailedJokeUiModel(private val text: String) : JokeUiModel(text, "") {
    override fun getIconResId() = 0
}


abstract class JokeUiModel(private val text: String, private val punchline: String) {
    protected fun mapToUi() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(mapToUi())
        provideIconRes(getIconResId())
    }
}