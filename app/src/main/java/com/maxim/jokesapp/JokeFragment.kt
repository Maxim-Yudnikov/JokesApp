package com.maxim.jokesapp

import com.maxim.jokesapp.presentation.BaseCommunication
import com.maxim.jokesapp.presentation.BaseViewModel

class JokeFragment: BaseFragment<Int>() {
    override fun getViewModel(app: JokeApp) = app.viewModel

    override fun getCommunication(app: JokeApp)  = app.jokeCommunication

    override fun checkBoxText() = "Show favorite jokes"

    override fun actionButtonText() = "Get joke"
}