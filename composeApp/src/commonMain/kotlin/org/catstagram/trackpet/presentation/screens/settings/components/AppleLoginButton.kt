package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.runtime.Composable
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_apple
import catstagrammp.composeapp.generated.resources.sign_with_apple
import com.mmk.kmpauth.firebase.apple.AppleButtonUiContainer
import dev.gitlive.firebase.auth.FirebaseUser
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppleLoginButton(signInWithApple: (Result<FirebaseUser?>) -> Unit) {
    val colors = LocalAppColors.current

    AppleButtonUiContainer(
        onResult = signInWithApple,
        linkAccount = false
    ) {
        RowWithIcon(
            onClick = { this.onClick() },
            icon = painterResource(Res.drawable.ic_apple),
            iconColor = colors.onBackground,
            text = stringResource(Res.string.sign_with_apple)
        )
    }
}