package org.catstagram.trackpet.presentation.screens.chatbot.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_arrow_back
import catstagrammp.composeapp.generated.resources.ic_info
import catstagrammp.composeapp.generated.resources.ic_new_chat
import org.catstagram.trackpet.presentation.shared.RoundedIcon
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChatBotTopBar(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onNewChatClick: () -> Unit
) {
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundedIcon(
            iconPainterRes = painterResource(Res.drawable.ic_arrow_back),
            onClick = onBackClick
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
        ) {
            RoundedIcon(
                iconPainterRes = painterResource(Res.drawable.ic_new_chat),
                onClick = onNewChatClick
            )

            RoundedIcon(
                iconPainterRes = painterResource(Res.drawable.ic_info),
                onClick = onInfoClick
            )
        }
    }
}