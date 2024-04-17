package com.example.wealthai.presentation.comman.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.wealthai.R

@Composable
fun CustomOutlineTextField(
    value: String,
    placeHolder: String,
    onClickListener: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onClickListener(it)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(text = placeHolder)
        },
        maxLines = 1,
        singleLine = true,
    )
}

@Composable
fun CustomLeadingOutlineTextField(
    value: String,
    placeHolder: String,
    leadingIcon: Painter,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        placeholder = {
            Text(text = placeHolder)
        },
        leadingIcon = {
            Icon(
                painter = leadingIcon,
                contentDescription = "sf;ms"
            )
        },
        maxLines = 1,
        singleLine = true,
    )
}