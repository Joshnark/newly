package com.orange.newly.feature.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.newly.domain.usecases.GetNewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getNewUseCase: GetNewUseCase
): ViewModel() {

    private val _state = MutableStateFlow<DetailUiState>(DetailUiState.Idle)
    val state = _state.asStateFlow()

    fun setEvent(intent: DetailIntent) {
        when(intent) {
            is DetailIntent.LoadNew -> loadNew(intent.id)
        }
    }

    private fun loadNew(id: String) {
        viewModelScope.launch {
            when(val result = getNewUseCase.invoke(id)) {
                is Failure -> _state.update { DetailUiState.Error(result.reason) }
                is Success -> _state.update { DetailUiState.NewLoadSuccess(result.value) }
            }
        }
    }

}