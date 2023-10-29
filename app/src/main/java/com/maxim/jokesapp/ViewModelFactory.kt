package com.maxim.jokesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxim.jokesapp.core.domain.CommonInteractor
import com.maxim.jokesapp.core.presentation.CommonCommunication
import com.maxim.jokesapp.presentation.BaseViewModel
import java.lang.IllegalStateException

class ViewModelFactory(
    private val jokesModel: JokesModule,
    private val quotesModel: QuotesModule
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val module = when {
            modelClass.isAssignableFrom(JokesViewModel::class.java) -> jokesModel
            modelClass.isAssignableFrom(QuotesViewModel::class.java) -> quotesModel
            else -> throw IllegalStateException("unknown type of viewModel")
        }
        return module.getViewModel() as T
    }
}


class JokesViewModel(interactor: CommonInteractor<Int>, communication: CommonCommunication<Int>) :
    BaseViewModel<Int>("jokes", interactor, communication)

class QuotesViewModel(
    interactor: CommonInteractor<String>,
    communication: CommonCommunication<String>
) :
    BaseViewModel<String>("quotes", interactor, communication)