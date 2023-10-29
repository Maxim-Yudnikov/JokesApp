package com.maxim.jokesapp

import com.maxim.jokesapp.core.presentation.CommonCommunication
import com.maxim.jokesapp.core.presentation.CommonViewModel

class QuoteFragment: BaseFragment<String>() {
    override fun getViewModel(app: JokeApp) = app.quoteViewModel
    override fun getCommunication(app: JokeApp) = app.quoteCommunication
    override fun checkBoxText() = "Show favorite quote"
    override fun actionButtonText() = "Get quote"
}