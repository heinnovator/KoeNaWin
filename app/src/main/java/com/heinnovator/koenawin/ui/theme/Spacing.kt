package com.heinnovator.koenawin.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val appBarPadding: Dp = 10.dp,
    val marginPadding: Dp = 20.dp,
    val linePadding: Dp = 12.dp
)

val localSpacing = compositionLocalOf { Spacing() }

val spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = localSpacing.current