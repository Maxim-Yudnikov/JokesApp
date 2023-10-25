package com.maxim.jokesapp

import androidx.annotation.DrawableRes

sealed class State {
    object Progress: State() {
        override fun show(progress: ShowView, button: EnableView) {
            progress.show(true)
            button.enable(false)
        }

    }
    data class Initial(val text: String, @DrawableRes val id: Int) : State() {
        override fun show(progress: ShowView, button: EnableView) {
            progress.show(false)
            button.enable(true)
        }

        override fun show(textView: ShowText, imageButton: ShowImage) {
            textView.show(text)
            imageButton.show(id)
        }

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


interface Show<T> {
    fun show(arg: T)
}
interface ShowText : Show<String>
interface ShowImage : Show<Int>
interface ShowView : Show<Boolean>

interface EnableView {
    fun enable(enable: Boolean)
}