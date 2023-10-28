package com.maxim.jokesapp

import com.maxim.jokesapp.domain.CommonItem

fun List<CommonItem>.toUiList() = map {it.to()}