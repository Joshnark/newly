package com.orange.newly.feature.detail.viewmodel

import com.orange.newly.domain.errors.AppError
import com.orange.newly.domain.models.New

sealed interface DetailUiState {
    data object Idle: DetailUiState
    data class Error(val error: AppError): DetailUiState
    data class NewLoadSuccess(val new: New): DetailUiState
}