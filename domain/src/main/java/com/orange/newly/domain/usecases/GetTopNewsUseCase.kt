package com.orange.newly.domain.usecases

import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.New
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<Result<List<New>, AppError>> {
        return flow {
            newsRepository
                .getTopNews()
                .collect {
                    emit(Success(it))
                }
        } // I did this flow wrapper out of convenience, actually the repository should return the result already wrapped
    }

}