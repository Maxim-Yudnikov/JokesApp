package com.maxim.jokesapp.domain

import com.maxim.jokesapp.presentation.BaseFailure
import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.presentation.BaseCommonUiModel
import com.maxim.jokesapp.presentation.FailedCommonUiModel
import com.maxim.jokesapp.presentation.FavoriteCommonUiModel
import com.maxim.jokesapp.presentation.CommonUiModel

sealed class CommonItem<E> : Mapper<CommonUiModel<E>> {
    class Success<E>(
        private val id: E,
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ) : CommonItem<E>() {
        override fun to() = if(favorite)
                FavoriteCommonUiModel(id, firstText, secondText)
            else
                BaseCommonUiModel(firstText, secondText)


    }

    class Failed(private val failure: BaseFailure) : CommonItem<Unit>() {
        override fun to(): CommonUiModel<Unit> {
            return FailedCommonUiModel(failure.getMessage())
        }
    }
}