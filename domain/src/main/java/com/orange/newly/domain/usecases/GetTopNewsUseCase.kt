package com.orange.newly.domain.usecases

import androidx.paging.PagingData
import com.orange.newly.domain.NewsRepository
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(category: Category = Category.GENERAL): Flow<PagingData<New>> {
        return newsRepository.getSectionNews(category)
    }

}