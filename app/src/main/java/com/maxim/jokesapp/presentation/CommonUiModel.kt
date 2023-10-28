package com.maxim.jokesapp.presentation

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.R
import com.maxim.jokesapp.core.presentation.Communication
import com.maxim.jokesapp.core.presentation.ShowText

class BaseCommonUiModel(private val text: String, private val punchline: String) : CommonUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_border_24
}

class FavoriteCommonUiModel(private val text: String, private val punchline: String) :
    CommonUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_24
}

class FailedCommonUiModel(private val text: String) : CommonUiModel(text, "") {
    override fun getIconResId() = 0
    override fun text() = text
    override fun show(communication: Communication) = communication.showState(
        State.Failed(
            text(),
            getIconResId()
        )
    )
}


abstract class CommonUiModel(private val first: String, private val second: String) {
    protected open fun text() = "$first\n$second"

    @DrawableRes
    protected abstract fun getIconResId(): Int

    open fun show(communication: Communication) = communication.showState(
        State.Initial(
            text(),
            getIconResId()
        )
    )

    fun show(showText: ShowText) = showText.show(text())
}