package org.catstagram.trackpet.presentation.screens.chatbot.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.about_assistant
import catstagrammp.composeapp.generated.resources.ic_arrow_back
import org.catstagram.trackpet.presentation.shared.RoundedIcon
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChatBotInfoTopBar(onBackClick: () -> Unit) {
    val colors = LocalAppColors.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundedIcon(
            iconPainterRes = painterResource(Res.drawable.ic_arrow_back),
            onClick = onBackClick
        )

        Text(
            text = stringResource(Res.string.about_assistant),
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            color = colors.onBackground
        )

        Spacer(modifier = Modifier.width(48.dp))
    }
}