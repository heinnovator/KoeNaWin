package com.heinnovator.koenawin.data

import androidx.compose.runtime.Stable

@Stable
data class Item(
    val id: Int,
    val title: String,
    val desc: String,
    val count: Int,
    val level: Int,
    val day: Int
)
