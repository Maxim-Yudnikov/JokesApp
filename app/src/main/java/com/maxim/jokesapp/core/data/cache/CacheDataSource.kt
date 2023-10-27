package com.maxim.jokesapp.core.data.cache

import com.maxim.jokesapp.core.data.ChangeStatus
import com.maxim.jokesapp.core.data.DataFetcher

interface CacheDataSource<E> : ChangeStatus<E>, DataFetcher<E>