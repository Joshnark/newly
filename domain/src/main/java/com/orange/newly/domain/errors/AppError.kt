package com.orange.newly.domain.errors

interface AppError

data class GeneralError(val throwable: Throwable? = null): AppError