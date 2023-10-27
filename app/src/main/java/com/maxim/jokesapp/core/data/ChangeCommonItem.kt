package com.maxim.jokesapp.core.data

import com.maxim.jokesapp.data.CommonDataModel
import java.lang.IllegalStateException

interface ChangeCommonItem {
    suspend fun change(changeJokeStatus: ChangeStatus) : CommonDataModel

    class Empty() : ChangeCommonItem {
        override suspend fun change(changeJokeStatus: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change joke called")
        }
    }
}