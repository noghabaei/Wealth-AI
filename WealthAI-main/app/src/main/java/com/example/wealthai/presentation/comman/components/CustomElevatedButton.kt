package com.example.wealthai.presentation.comman.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.wealthai.R

@Composable
fun CustomElevatedButton(
    text: String,
    onClickListener: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = ButtonDefaults.buttonColors( colorResource(id = R.color.login_button_blue)),
        onClick = { onClickListener() },
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text,style = TextStyle(color = Color.White))
    }
}