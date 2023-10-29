package com.maxim.jokesapp.presentation

import com.maxim.jokesapp.JokesViewModel

class JokeFragment: BaseFragment<JokesViewModel, Int>() {
    override fun checkBoxText() = "Show favorite jokes"

    override fun actionButtonText() = "Get joke"
    override fun getViewModelClass() = JokesViewModel::class.java
}