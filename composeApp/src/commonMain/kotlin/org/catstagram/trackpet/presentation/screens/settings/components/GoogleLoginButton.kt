package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.runtime.Composable
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_google
import catstagrammp.composeapp.generated.resources.sign_with_google
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import dev.gitlive.firebase.auth.FirebaseUser
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GoogleLoginButton(signInWithGoogle: (Result<FirebaseUser?>) -> Unit) {
    GoogleButtonUiContainerFirebase(
        onResult = signInWithGoogle,
        linkAccount = false,
        filterByAuthorizedAccounts = false
    ) {
        RowWithIcon(
            onClick = { this.onClick() },
            icon = painterResource(Res.drawable.ic_google),
            text = stringResource(Res.string.sign_with_google)
        )
    }
}