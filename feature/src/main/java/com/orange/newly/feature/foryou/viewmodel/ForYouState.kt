package com.orange.newly.feature.foryou.viewmodel

import com.orange.newly.domain.models.New

sealed interface ForYouState {
    data object Initial: ForYouState
    data object ErrorLoadingRecommended: ForYouState
}