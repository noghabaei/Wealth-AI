package com.example.wealthai.presentation.auth.login

import android.app.Activity
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wealthai.R
import com.example.wealthai.presentation.auth.AuthViewModel
import com.example.wealthai.presentation.auth.login.components.OneTapSignIn
import com.example.wealthai.presentation.auth.login.components.SignInWithGoogle
import com.example.wealthai.presentation.comman.components.ClickableTextComposable
import com.example.wealthai.presentation.comman.components.CustomBoldText
import com.example.wealthai.presentation.comman.components.CustomElevatedButton
import com.example.wealthai.presentation.comman.components.CustomLeadingOutlineTextField
import com.example.wealthai.presentation.comman.components.GoogleButton
import com.example.wealthai.presentation.comman.components.PasswordOutlineTextField
import com.example.wealthai.presentation.screens.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(navController: NavController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var showPassword by remember { mutableStateOf(value = false) }
    systemUiController.setSystemBarsColor(
        color = colorResource(id = R.color.white)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {

        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Column {
            Text(modifier = Modifier.padding(0.dp,50.dp,0.dp,0.dp), text = stringResource(R.string.welcome), style = MaterialTheme.typography.h1)
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
            PasswordOutlineTextField(
                value = password,
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                placeHolder = stringResource(id = R.string.password)
            ) {
                password = it
            }

            Spacer(modifier = Modifier.height(80.dp))
            CustomElevatedButton(text = stringResource(id = R.string.login)) {
                authViewModel.login(email,password){
                   // Toast.makeText(context,"Log in Succesful ", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.CompanyListingScreen.route)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            ClickableTextComposable(stringResource(id = R.string.dont_have_account),stringResource(id = R.string.signup)){
                navController.navigate(Screen.SignUpScreen.route)
            }

                /*
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
            }*/
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.continue_with),
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                GoogleButton {
                    authViewModel.oneTapSignIn()
                }

        }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credentials = authViewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                authViewModel.signInWithGoogle(googleCredentials){
                        token ->

                }
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )

    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navController.navigate(Screen.CompanyListingScreen.route)
            }
        }
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}