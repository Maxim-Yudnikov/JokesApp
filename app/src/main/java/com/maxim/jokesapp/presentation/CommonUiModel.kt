package com.maxim.jokesapp.presentation

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.core.presentation.Communication
import com.maxim.jokesapp.R
import com.maxim.jokesapp.core.presentation.ShowText

class BaseCommonUiModel<E>(private val text: String, private val punchline: String) :
    CommonUiModel<E>(text, punchline) {
    override fun getIconResId() = R.drawable.ic_favorite_border_24
}

class FavoriteCommonUiModel<E>(private val id: E, private val text: String, private val punchline: String) :
    CommonUiModel<E>(text, punchline) {
    override fun same(model: CommonUiModel<E>): Boolean {
        return model is FavoriteCommonUiModel<E> && model.id == id
    }
    override fun getIconResId() = R.drawable.ic_favorite_24
    override fun matches(id: E): Boolean  = this.id == id

    override fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<E>) {
        listener.change(id)
    }
}

class FailedCommonUiModel<E>(private val text: String) : CommonUiModel<E>(text, "") {
    override fun getIconResId() = 0
    override fun text() = text
    override fun show(communication: Communication) = communication.showState(
        State.Failed(
            text(),
            getIconResId()
        )
    )
}


abstract class CommonUiModel<T>(private val first: String, private val second: String) {
    open fun same(model: CommonUiModel<T>): Boolean = false
    protected open fun text() = "$first\n$second"

    open fun matches(id: T): Boolean = false
    open fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<T>) = Unit
    fun show(showText: ShowText) = showText.show(text())

    @DrawableRes
    protected abstract fun getIconResId(): Int

    open fun show(communication: Communication) = communication.showState(
        State.Initial(
            text(),
            getIconResId()
        )
    )
}