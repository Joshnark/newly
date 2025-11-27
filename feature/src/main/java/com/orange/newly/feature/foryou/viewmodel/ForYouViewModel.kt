package com.orange.newly.feature.foryou.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.newly.domain.usecases.GetPopularNewsUseCase
import com.orange.newly.domain.usecases.GetTopNewsUseCase
import com.orange.newly.domain.usecases.RefreshPopularNewsUseCase
import com.orange.newly.domain.usecases.RefreshTopNewsUseCase
import com.orange.newly.feature.foryou.errors.ErrorRefreshingPopularNews
import com.orange.newly.feature.foryou.errors.ErrorRefreshingTopNews
import com.orange.newly.feature.shared.extensions.fold
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forkhandles.result4k.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val getTopNewsUseCase: GetTopNewsUseCase,
    private val getPopularNewsUseCase: GetPopularNewsUseCase,
    private val refreshPopularNewsUseCase: RefreshPopularNewsUseCase,
    private val refreshTopNewsUseCase: RefreshTopNewsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ForYouUIState())
    val state = _state.asStateFlow()

    fun setIntent(intent: ForYouIntent) {
        when(intent) {
            is ForYouIntent.LoadForYouNews -> loadData()
            is ForYouIntent.RefreshAll -> refreshAll()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            getTopNewsUseCase
                .invoke()
                .fold(
                    onSuccess = { result ->
                        _state.update {
                            it.copy(
                                topNews = result,
                            )
                        }
                    },
                    onFailure = { error ->
                        _state.update {
                            it.copy(
                                errorLoadingTopNews = error
                            )
                        }
                    }
                )

            getPopularNewsUseCase
                .invoke()
                .fold(
                    onSuccess = { result ->
                        _state.update {
                            it.copy(
                                popularNews = result,
                            )
                        }
                    },
                    onFailure = { error ->
                        _state.update {
                            it.copy(
                                errorLoadingPopularNews = error
                            )
                        }
                    }
                )
        }
    }

    private fun refreshAll() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            val topResult = refreshTopNewsUseCase.invoke()
            val popularResult = refreshPopularNewsUseCase.invoke()

            val error = when {
                topResult is Failure -> ErrorRefreshingTopNews
                popularResult is Failure -> ErrorRefreshingPopularNews
                else -> null
            }

            viewModelScope.launch(Dispatchers.Main) {
                _state.update {
                    it.copy(refreshError = error, isLoading = false)
                }
            }
        }
    }

}