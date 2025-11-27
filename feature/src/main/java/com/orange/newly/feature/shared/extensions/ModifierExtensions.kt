package com.orange.newly.feature.shared.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.orange.newly.feature.shared.Sizes

fun Modifier.paddingTiny() = this.padding(Sizes.TINY)

fun Modifier.paddingSmall() = this.padding(Sizes.SMALL)

fun Modifier.paddingMedium() = this.padding(Sizes.MEDIUM)

fun Modifier.paddingLarge() = this.padding(Sizes.LARGE)