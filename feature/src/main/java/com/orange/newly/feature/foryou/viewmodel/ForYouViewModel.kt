package com.orange.newly.feature.foryou.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orange.newly.domain.models.Category
import com.orange.newly.domain.models.New
import com.orange.newly.domain.usecases.GetTopNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    getTopNewsUseCase: GetTopNewsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<ForYouState>(ForYouState.Initial)
    val state: StateFlow<ForYouState> = _state.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val recommendedNewsPagingData: Flow<PagingData<New>> = getTopNewsUseCase.invoke(
        Category.GENERAL
    ).cachedIn(viewModelScope).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PagingData.empty()
    )

    fun setIntent(intent: ForYouIntent) {
        when(intent) {
            is ForYouIntent.LoadLocalizedNews -> Unit
        }
    }

}