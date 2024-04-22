package com.example.wealthai.domain.implementation

import com.example.wealthai.domain.model.Response
import com.example.wealthai.domain.repository.AuthRepository
import com.example.wealthai.domain.repository.OneTapSignInResponse
import com.example.wealthai.domain.repository.SignInWithGoogleResponse
import com.example.wealthai.presentation.auth.login.components.LoginConstants.SIGN_IN_REQUEST
import com.example.wealthai.presentation.auth.login.components.LoginConstants.SIGN_UP_REQUEST
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepository {



    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Response.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Response.Success(signUpResult)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential,
        completion: (String?) -> Unit
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                val user = auth.currentUser
                if (user != null) {
                    // Save additional user data to Firestore

                }
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}