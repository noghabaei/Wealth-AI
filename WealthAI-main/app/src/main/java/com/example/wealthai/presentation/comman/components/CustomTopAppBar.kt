package com.example.wealthai.presentation.comman.components

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.wealthai.ui.theme.Purple200

@Composable
fun CustomTopAppBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.h1,
                color = Color.White
            )
        },
        backgroundColor = Purple200,
        contentColor = Color.White,
        elevation = AppBarDefaults.TopAppBarElevation
    )
}