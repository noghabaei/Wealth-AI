package com.example.wealthai.domain.repository
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.example.wealthai.domain.model.Response
import com.google.firebase.auth.AuthCredential

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>

interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential, completion: (String?) -> Unit): SignInWithGoogleResponse

}