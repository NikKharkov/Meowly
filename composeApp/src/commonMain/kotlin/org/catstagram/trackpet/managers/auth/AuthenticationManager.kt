package org.catstagram.trackpet.managers.auth

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import org.catstagram.trackpet.domain.settings.User

class AuthenticationManager(private val auth: FirebaseAuth) {
    fun handleAuthResult(result: Result<FirebaseUser?>): User? {
        return try {
            val firebaseUser = result.getOrNull()
            firebaseUser?.let { user ->
                User(
                    id = user.uid,
                    name = user.displayName,
                    profilePicture = user.photoURL
                )
            }
        } catch (e: Exception) {
            println("Firebase auth result handling failed: ${e.message}")
            null
        }
    }

    fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentUser(): User? {
        return auth.currentUser?.let { user ->
            println("Current user: $user")
            User(
                id = user.uid,
                name = user.displayName,
                profilePicture = user.photoURL
            )
        }
    }

    suspend fun signOut() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            println("Sign-out failed: ${e.message}")
        }
    }
}