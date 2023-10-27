package com.maxim.jokesapp.presentation

import androidx.annotation.DrawableRes
import com.maxim.jokesapp.core.presentation.EnableView
import com.maxim.jokesapp.core.presentation.ShowImage
import com.maxim.jokesapp.core.presentation.ShowText
import com.maxim.jokesapp.core.presentation.ShowView

sealed class State {
    protected abstract val type: Int
    companion object {
        const val INITIAL = 0
        const val PROGRESS = 1
        const val FAILED = 2
    }
    fun isType(type: Int) = this.type == type

    object Progress: State() {
        override val type = 1

        override fun show(progress: ShowView, button: EnableView) {
            progress.show(true)
            button.enable(false)
        }

    }

    abstract class Info(private val text: String, @DrawableRes private val id: Int) : State() {
        override fun show(progress: ShowView, button: EnableView) {
            progress.show(false)
            button.enable(true)
        }
        override fun show(textView: ShowText, imageButton: ShowImage) {
            textView.show(text)
            imageButton.show(id)
        }
    }

    open class Initial(private val text: String, @DrawableRes private val id: Int) : Info(text, id) {
        override val type = 0
    }

    class Failed(private val text: String, @DrawableRes private val id: Int) : Info(text, id) {
        override val type = 2
    }

    fun show(
        progress: ShowView,
        button: EnableView,
        textView: ShowText,
        imageButton: ShowImage
    ) {
        show(progress, button)
        show(textView, imageButton)
    }

    protected open fun show(progress: ShowView, button: EnableView) {}
    protected open fun show(textView: ShowText, imageButton: ShowImage) {}
}