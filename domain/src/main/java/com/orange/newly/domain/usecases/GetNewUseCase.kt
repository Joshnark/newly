package com.orange.newly.domain.usecases

import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.New
import dev.forkhandles.result4k.Result
import javax.inject.Inject

class GetNewUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(id: String): Result<New, AppError> {
        return newsRepository
            .getNewById(id)
    }

}