package com.example.wealthai.presentation.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wealthai.R
import com.example.wealthai.presentation.comman.components.CustomElevatedButton
import com.example.wealthai.presentation.comman.components.CustomLeadingOutlineTextField

@Composable
fun SignUpScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        
        topBar = {
            TopAppBar (
                title = { Text(text = "")},
                navigationIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Go Back")
                }
            )
        }
    ) {innerPadding ->
        var name by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.h1
            )
//            CustomBoldText(text = stringResource(R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))
            CustomLeadingOutlineTextField(
                value = name,
                leadingIcon = painterResource(id = R.drawable.ic_user),
                placeHolder = stringResource(id = R.string.email)
            ) {
                name = it
            }
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
            CustomElevatedButton(text = stringResource(id = R.string.signup)) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.h2
                )
            }

        }
    }
}