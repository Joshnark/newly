package com.orange.newly.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import com.orange.newly.domain.usecases.GetCategoryNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val getCategoryNewsUseCase: GetCategoryNewsUseCase
): ViewModel() {

    private val _pagingParams = MutableStateFlow<Category?>(null)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val newsPagingData: Flow<PagingData<New>> = _pagingParams
        .filterNotNull()
        .flatMapLatest { params ->
            getCategoryNewsUseCase.invoke(
                category = params
            ).flowOn(Dispatchers.IO)
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    fun setIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.LoadCategory -> loadCategory(intent.category)
        }
    }

    private fun loadCategory(category: Category) {
        _pagingParams.value = category
    }

}