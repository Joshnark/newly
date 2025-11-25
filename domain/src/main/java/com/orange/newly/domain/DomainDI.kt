package com.orange.newly.domain

import com.orange.newly.domain.usecases.GetTopNewsUseCase
import com.orange.newly.domain.usecases.SearchNewsUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DomainDI {

    @Binds
    fun bindGetTopNewsUseCase(getTopNewsUseCase: GetTopNewsUseCase) = getTopNewsUseCase

    @Binds
    fun bindSearchNewsUseCase(searchNewsUseCase: SearchNewsUseCase) = searchNewsUseCase

}