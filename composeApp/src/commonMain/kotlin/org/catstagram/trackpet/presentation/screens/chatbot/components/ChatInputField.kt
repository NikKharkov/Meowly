package org.catstagram.trackpet.presentation.screens.chatbot.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ask_me_anything
import catstagrammp.composeapp.generated.resources.ic_send
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChatInputField(onSendMessage: (String) -> Unit) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            placeholder = {
                Text(
                    text = stringResource(Res.string.ask_me_anything),
                    color = colors.secondary
                )
            },
            shape = RoundedCornerShape(dimensions.paddingMedium),
            modifier = Modifier.weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colors.onBackground,
                unfocusedTextColor = colors.onBackground,
                unfocusedContainerColor = colors.primary,
                focusedContainerColor = colors.primary,
                focusedBorderColor = colors.onBackground,
                unfocusedBorderColor = colors.onBackground
            )
        )

        Icon(
            painter = painterResource(Res.drawable.ic_send),
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier
                .size(48.dp)
                .pressAnimation(
                    onClick = {
                        if (message.isNotBlank()) {
                            onSendMessage(message)
                            message = ""
                        }
                    }
                )
                .padding(start = dimensions.paddingSmall)
        )
    }
}