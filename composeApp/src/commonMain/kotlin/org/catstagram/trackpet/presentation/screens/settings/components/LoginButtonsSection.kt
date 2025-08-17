package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.account
import catstagrammp.composeapp.generated.resources.ic_logout
import catstagrammp.composeapp.generated.resources.log_out
import dev.gitlive.firebase.auth.FirebaseUser
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginButtonsSection(
    handleAuth: (Result<FirebaseUser?>) -> Unit,
    onLogOutClick: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Text(
            text = stringResource(Res.string.account),
            fontSize = 14.sp,
            color = colors.secondary
        )

        GoogleLoginButton(signInWithGoogle = handleAuth)

        AppleLoginButton(signInWithApple= handleAuth)

        RowWithIcon(
            onClick = onLogOutClick,
            icon = painterResource(Res.drawable.ic_logout),
            iconColor = colors.onBackground,
            text = stringResource(Res.string.log_out)
        )
    }
}