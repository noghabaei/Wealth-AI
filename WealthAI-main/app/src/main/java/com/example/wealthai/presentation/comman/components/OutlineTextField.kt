package com.example.wealthai.presentation.comman.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wealthai.R
import com.example.wealthai.presentation.screens.Screen


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
fun PasswordOutlineTextField(
    value: String,
    placeHolder: String,
    leadingIcon: Painter,
    onValueChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(value = false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(60.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(
                width = 1.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(20.dp)
            ),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = stringResource(id = R.string.password)
            )


        },
        singleLine = true,

        placeholder = {
            Text(text = placeHolder)
        },
        visualTransformation = if (showPassword) {

            VisualTransformation.None

        } else {

            PasswordVisualTransformation()

        },
        leadingIcon = {
            Icon(
                painter = leadingIcon,
                contentDescription = "sf;ms"
            )
        },
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent, // Change the outline color when focused
            unfocusedBorderColor = Color.Transparent, // Change the outline color when not focused
        )

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
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(20.dp)
            ),
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent, // Change the outline color when focused
            unfocusedBorderColor = Color.Transparent, // Change the outline color when not focused
        )
    )
}

@Composable
fun ClickableTextComposable(firstText:String, clickableText: String, navigate:()->Unit){
    val text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        ) {
            append(firstText)
        }
        append(" ")
        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                fontSize = 16.sp
            )
        ) {
            append(clickableText)
        }
    }
    ClickableText(text = text, onClick = { offset ->
        text.getStringAnnotations(tag = "click", start = offset, end = offset)
            .firstOrNull()
            ?.let {
                navigate()                         // on click operation here
                // Toast.makeText(context, "Conditions Clicked", Toast.LENGTH_LONG).show()
            }
    })
}
