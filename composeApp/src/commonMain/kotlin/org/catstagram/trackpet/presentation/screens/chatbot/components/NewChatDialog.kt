package org.catstagram.trackpet.presentation.screens.chatbot.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.cancel
import catstagrammp.composeapp.generated.resources.new_chat_confirm
import catstagrammp.composeapp.generated.resources.new_chat_message
import catstagrammp.composeapp.generated.resources.new_chat_title
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewChatDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colors.primary,
        shape = RoundedCornerShape(dimensions.paddingMedium),
        title = {
            Text(
                text = stringResource(Res.string.new_chat_title),
                color = colors.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.new_chat_message),
                color = colors.secondary,
                fontSize = 16.sp
            )
        },
        confirmButton = {
            Surface(
                onClick = onConfirm,
                shape = RoundedCornerShape(dimensions.paddingSmall),
                color = Color.Red.copy(alpha = 0.1f),
                modifier = Modifier.pressAnimation()
            ) {
                Text(
                    text = stringResource(Res.string.new_chat_confirm),
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(
                        horizontal = dimensions.paddingMedium,
                        vertical = dimensions.paddingSmall
                    )
                )
            }
        },
        dismissButton = {
            Surface(
                onClick = onDismiss,
                shape = RoundedCornerShape(dimensions.paddingSmall),
                color = Color.Transparent,
                modifier = Modifier.pressAnimation()
            ) {
                Text(
                    text = stringResource(Res.string.cancel),
                    color = colors.secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(
                        horizontal = dimensions.paddingMedium,
                        vertical = dimensions.paddingSmall
                    )
                )
            }
        }
    )
}