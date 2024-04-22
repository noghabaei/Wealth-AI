package com.example.wealthai.presentation.auth.login.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wealthai.domain.model.Response
import com.example.wealthai.presentation.auth.AuthViewModel

@Composable
fun SignInWithGoogle(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit
) {
    when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
    }
}