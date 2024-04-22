package com.example.wealthai.presentation.auth.login.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wealthai.domain.model.Response
import com.example.wealthai.presentation.auth.AuthViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult

@Composable
fun OneTapSignIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
    }
}