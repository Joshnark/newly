package com.orange.newly.feature.shared.extensions

import com.orange.newly.domain.errors.GeneralError
import com.orange.newly.domain.errors.AppError
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

suspend fun <T> Flow<Result<T, AppError>>.fold(onSuccess: (T) -> Unit, onFailure: (AppError) -> Unit) {
    this.apply {
        catch {
            onFailure.invoke(GeneralError(it))
        }.collect {
            when(it) {
                is Failure -> onFailure.invoke(it.reason)
                is Success -> onSuccess.invoke(it.value)
            }
        }
    }
}