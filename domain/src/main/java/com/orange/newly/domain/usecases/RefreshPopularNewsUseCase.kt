package com.orange.newly.domain.usecases

import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.errors.AppError
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import javax.inject.Inject

class RefreshPopularNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke() = newsRepository.refreshPopularNews()

}