package com.orange.newly.feature.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import com.orange.newly.domain.usecases.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchNewsUseCase: SearchNewsUseCase
): ViewModel() {

    private val _pagingParams = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val newsPagingData: Flow<PagingData<New>> = _pagingParams
        .filterNotNull()
        .flatMapLatest { params ->
            searchNewsUseCase.invoke(
                query = params
            ).flowOn(Dispatchers.IO)
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    fun setIntent(intent: SearchIntent) {
        when(intent) {
            is SearchIntent.Search -> changeQuery(intent.query)
        }
    }

    private fun changeQuery(query: String) {
        if (_pagingParams.value == query) return

        _pagingParams.value = query
    }

}