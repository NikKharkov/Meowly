package org.catstagram.trackpet.presentation.screens.chatbot.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.catgpt_doing_magic
import org.catstagram.trackpet.presentation.shared.Status
import org.catstagram.trackpet.presentation.shared.StatusAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.stringResource

@Composable
fun GeneratingIndicator() {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(dimensions.paddingMedium),
            shadowElevation = 4.dp,
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .background(brush = colors.catCardGradient)
                    .padding(dimensions.paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StatusAnimation(
                    status = Status.LOADING,
                    animationSize = 80.dp
                )

                Text(
                    text = stringResource(Res.string.catgpt_doing_magic),
                    color = colors.onBackground,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = dimensions.paddingSmall)
                )
            }
        }
    }
}