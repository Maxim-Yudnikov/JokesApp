package com.maxim.jokesapp.presentation

import com.maxim.jokesapp.JokeApp
import com.maxim.jokesapp.QuotesViewModel

class QuoteFragment: BaseFragment<QuotesViewModel, String>() {
    override fun checkBoxText() = "Show favorite quote"
    override fun actionButtonText() = "Get quote"
    override fun getViewModelClass() = QuotesViewModel::class.java
}