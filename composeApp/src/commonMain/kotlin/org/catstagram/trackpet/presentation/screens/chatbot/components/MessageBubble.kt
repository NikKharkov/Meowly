package org.catstagram.trackpet.presentation.screens.chatbot.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_download
import coil3.compose.AsyncImage
import org.catstagram.trackpet.domain.chatbot.ChatMessageItem
import org.catstagram.trackpet.domain.chatbot.MessageType
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource

@Composable
fun MessageBubble(
    messageItem: ChatMessageItem,
    isTablet: Boolean,
    saveImage: (String) -> Unit,
    isSaving: Boolean = false
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxBubbleWidth = if (isTablet) 400.dp else 280.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (messageItem.isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            modifier = Modifier.widthIn(max = maxBubbleWidth),
            shape = RoundedCornerShape(dimensions.paddingMedium),
            shadowElevation = 4.dp,
            color = if (messageItem.isUserMessage) colors.primary else Color.Transparent
        ) {
            when (messageItem.type) {
                MessageType.TEXT -> {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = if (messageItem.isUserMessage)
                                    SolidColor(colors.primary)
                                else colors.catCardGradient
                            )
                            .padding(dimensions.paddingMedium)
                    ) {
                        Text(
                            text = messageItem.content,
                            color = colors.onBackground,
                            fontSize = 16.sp
                        )
                    }
                }

                MessageType.IMAGE -> {
                    Box {
                        AsyncImage(
                            model = messageItem.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(dimensions.paddingSmall)),
                            contentScale = ContentScale.Crop
                        )

                        if (isSaving) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.BottomEnd)
                                    .padding(dimensions.paddingSmall),
                                color = colors.onBackground,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                painter = painterResource(Res.drawable.ic_download),
                                contentDescription = null,
                                tint = colors.onBackground,
                                modifier = Modifier
                                    .size(48.dp)
                                    .pressAnimation(onClick = {
                                        messageItem.imageUrl?.let { url ->
                                            saveImage(url)
                                        }
                                    })
                                    .align(Alignment.BottomEnd)
                                    .padding(dimensions.paddingSmall)
                            )
                        }
                    }
                }
            }
        }
    }
}
