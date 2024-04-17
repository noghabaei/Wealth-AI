package com.example.wealthai.presentation.comman.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomElevatedButton(
    text: String,
    onClickListener: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
        onClick = { onClickListener }) {
        Text(text = text)
    }
}