package com.example.wealthai.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wealthai.domain.model.Response
import com.example.wealthai.domain.repository.AuthRepository
import com.example.wealthai.domain.repository.OneTapSignInResponse
import com.example.wealthai.domain.repository.SignInWithGoogleResponse
import com.example.wealthai.util.Utils
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    val oneTapClient: SignInClient,
): ViewModel() {


    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Response.Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Response.Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(googleCredential: AuthCredential, completion: (String?) -> Unit) = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredential){
                token ->
            completion(token)
        }
    }


    fun signUp(email: String, password: String, fullName: String, callback: ()->Unit) {
        val auth = FirebaseAuth.getInstance()
        val db = Firebase.firestore

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-up success
                    val user = auth.currentUser
                    user?.let {
                        // Save additional user data to Firestore
                        val userData = hashMapOf(
                            "email" to email,
                            "fullName" to fullName,
                            "password" to password
                        )

                        db.collection(Utils.USERS)
                            .document(user.uid)
                            .set(userData)
                            .addOnSuccessListener {
                                print("Sign-up successful!")
                                callback()
                                // Navigate to the next screen or perform other actions
                            }
                            .addOnFailureListener { e ->
                                print("Failed to save user data. $e")
                            }
                    }
                } else {
                    // If sign-up fails, display a message to the user.
                    print("Sign-up failed. ${task.exception?.message}")
                }
            }
    }

    fun login(email: String, password: String, callback : ()-> Unit) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    print("Login successful!")
                    callback()
                    // Navigate to the next screen or perform other actions
                } else {
                    print("Login failed. ${task.exception?.message}")
                }
            }
    }
}