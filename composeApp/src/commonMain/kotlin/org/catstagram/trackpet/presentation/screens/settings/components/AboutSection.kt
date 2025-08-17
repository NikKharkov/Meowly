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
import catstagrammp.composeapp.generated.resources.about
import catstagrammp.composeapp.generated.resources.ic_privacy
import catstagrammp.composeapp.generated.resources.privacy_policy
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutSection(
    onPrivacyClick: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Text(
            text = stringResource(Res.string.about),
            fontSize = 14.sp,
            color = colors.secondary
        )

        RowWithIcon(
            onClick = onPrivacyClick,
            icon = painterResource(Res.drawable.ic_privacy),
            text = stringResource(Res.string.privacy_policy)
        )
    }
}