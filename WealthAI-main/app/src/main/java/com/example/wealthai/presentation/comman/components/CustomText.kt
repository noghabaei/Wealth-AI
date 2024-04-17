package com.example.wealthai.presentation.comman.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomBoldText(
    text: String
) {
    Text(text = text, style = MaterialTheme.typography.h1)
}