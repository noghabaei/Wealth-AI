package com.example.wealthai.presentation.auth.login

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wealthai.R
import com.example.wealthai.presentation.comman.components.CustomBoldText
import com.example.wealthai.presentation.comman.components.CustomElevatedButton
import com.example.wealthai.presentation.comman.components.CustomLeadingOutlineTextField

@Composable
fun LoginScreen() {
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)) {

        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Column {
            Text(text = stringResource(R.string.welcome), style = MaterialTheme.typography.h1)
//            CustomBoldText(text = stringResource(R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))
            CustomLeadingOutlineTextField(
                value = email,
                leadingIcon = painterResource(id = R.drawable.ic_mail),
                placeHolder = stringResource(id = R.string.email)
            ) {
                email = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            CustomLeadingOutlineTextField(
                value = password,
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                placeHolder = stringResource(id = R.string.password)
            ) {
                password = it
            }
            Spacer(modifier = Modifier.height(80.dp))
            CustomElevatedButton(text = stringResource(id = R.string.login)) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.dont_have_account),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.signup),
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}