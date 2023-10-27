package com.maxim.jokesapp.domain

import com.maxim.jokesapp.presentation.BaseFailure
import com.maxim.jokesapp.core.Mapper
import com.maxim.jokesapp.presentation.BaseCommonUiModel
import com.maxim.jokesapp.presentation.FailedCommonUiModel
import com.maxim.jokesapp.presentation.FavoriteCommonUiModel
import com.maxim.jokesapp.presentation.CommonUiModel

sealed class CommonItem : Mapper<CommonUiModel> {
    class Success(
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ) : CommonItem() {
        override fun to(): CommonUiModel {
            return if(favorite)
                FavoriteCommonUiModel(firstText, secondText)
            else
                BaseCommonUiModel(firstText, secondText)
        }

    }

    class Failed(private val failure: BaseFailure) : CommonItem() {
        override fun to(): CommonUiModel {
            return FailedCommonUiModel(failure.getMessage())
        }
    }
}