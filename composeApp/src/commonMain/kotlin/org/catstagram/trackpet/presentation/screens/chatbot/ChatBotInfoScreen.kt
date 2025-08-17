package org.catstagram.trackpet.presentation.screens.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.chatbot_box_label1
import catstagrammp.composeapp.generated.resources.chatbot_box_label2
import catstagrammp.composeapp.generated.resources.chatbot_box_label3
import catstagrammp.composeapp.generated.resources.chatbot_box_label4
import catstagrammp.composeapp.generated.resources.chatbot_box_text1
import catstagrammp.composeapp.generated.resources.chatbot_box_text2
import catstagrammp.composeapp.generated.resources.chatbot_box_text3
import catstagrammp.composeapp.generated.resources.chatbot_box_text4
import catstagrammp.composeapp.generated.resources.chatbot_desc
import catstagrammp.composeapp.generated.resources.chatbot_info_label
import catstagrammp.composeapp.generated.resources.chatbot_mascot
import org.catstagram.trackpet.presentation.screens.chatbot.components.ChatBotInfoTopBar
import org.catstagram.trackpet.presentation.screens.chatbot.components.DescriptionBox
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChatBotInfoScreen(onBackClick: () -> Unit) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center
    ) {
        val isTablet = maxWidth > 600.dp
        val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
        val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium

        Column(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(dimensions.paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            ChatBotInfoTopBar(onBackClick = onBackClick)

            Image(
                painter = painterResource(Res.drawable.chatbot_mascot),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(color = colors.primary),
                contentScale = ContentScale.Fit
            )

            Text(
                text = stringResource(Res.string.chatbot_info_label),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )

            Text(
                text = stringResource(Res.string.chatbot_desc),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = colors.secondary
            )

            DescriptionBox(
                label = stringResource(Res.string.chatbot_box_label1),
                description = stringResource(Res.string.chatbot_box_text1)
            )

            DescriptionBox(
                label = stringResource(Res.string.chatbot_box_label2),
                description = stringResource(Res.string.chatbot_box_text2)
            )

            DescriptionBox(
                label = stringResource(Res.string.chatbot_box_label3),
                description = stringResource(Res.string.chatbot_box_text3)
            )

            DescriptionBox(
                label = stringResource(Res.string.chatbot_box_label4),
                description = stringResource(Res.string.chatbot_box_text4)
            )
        }
    }
}

@Preview
@Composable
private fun ChatBotInfoPreview() {
    ChatBotInfoScreen(onBackClick = {})
}